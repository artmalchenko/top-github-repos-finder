package com.artmalchenko.topgithubreposfinder.service;

import com.artmalchenko.topgithubreposfinder.dto.TopGithubReposDTO;
import com.artmalchenko.topgithubreposfinder.dto.repository.GithubRepositoryDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static com.artmalchenko.topgithubreposfinder.util.Constants.*;
import static org.apache.commons.lang3.ObjectUtils.allNull;

@Service
public class TopGithubReposFinderService {

    private static final String GITHUB_SEARCH_REPOS_ENDPOINT = "https://api.github.com/search/repositories";
    private final RestTemplate restTemplate;

    public TopGithubReposFinderService(RestTemplate template) {
        this.restTemplate = template;
    }

    public ResponseEntity<TopGithubReposDTO> searchTopGithubRepos(LocalDate createdOnOrAfter, String language, Integer limit) {
        validateLimitFilter(limit);
        validateDateFilterIsBeforeOrEqualsToTodaysDate(createdOnOrAfter);

        UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(GITHUB_SEARCH_REPOS_ENDPOINT);

        URI uri = uriBuilder
                .queryParam("q", buildQuery(createdOnOrAfter, language))
                .queryParam("sort", "stars")
                .queryParam("order", "desc")
                .queryParam("per_page", limit)
                .queryParam("page", "1")
                .build();

        RequestEntity<Void> entity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<TopGithubReposDTO> response = restTemplate.exchange(entity, TopGithubReposDTO.class);

        if (language != null) {
            validateGithubResponseForLanguageFilter(response, language);
        }

        return response;
    }

    private void validateDateFilterIsBeforeOrEqualsToTodaysDate(LocalDate createdOnOrAfter) {
        if (createdOnOrAfter != null && createdOnOrAfter.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("CreatedOnOrAfter filter should be before or equals today.");
        }
    }

    private void validateLimitFilter(Integer limit) {
        if (limit != null && Stream.of(10, 50, 100).noneMatch(limit::equals)) {
            throw new IllegalArgumentException("Limit filter should be in [10,50,100]");
        }
    }

    private String buildQuery(LocalDate createdAfterDateFilter, String language) {
        if (allNull(createdAfterDateFilter, language)) {
            return GITHUB_SEARCH_EMPTY_QUERY;
        } else {
            StringBuilder query = new StringBuilder();

            if (createdAfterDateFilter != null) {
                buildQueryForCreatedAfterDateFilter(createdAfterDateFilter, query);
            }

            if (language != null && !language.isEmpty()) {
                buildQueryForLanguagesFilter(language, query);
            }

            return query.toString();
        }
    }

    private void buildQueryForCreatedAfterDateFilter(LocalDate createdAfterDateFilter, StringBuilder query) {
        query.append(GITHUB_SEARCH_CREATED_QUALIFIER).append(":").append(">=").append(createdAfterDateFilter);
    }

    private void buildQueryForLanguagesFilter(String language, StringBuilder query) {
        if (query.length() != 0) {
            query.append("+");
        }

        query.append(GITHUB_SEARCH_LANGUAGE_QUALIFIER).append(":").append(language);
    }

    /**
     * If you pass non-existent language as filter to GitHub search API it will
     * return projects where language=null or language={someRandomLanguage}.
     * We want to fail-fast in the case and don't return wrong data to client.
     */
    private void validateGithubResponseForLanguageFilter(ResponseEntity<TopGithubReposDTO> responseEntity,
                                                         String languageFilter) {
        if (responseEntity != null && responseEntity.getBody() != null
                && responseEntity.getBody().githubRepositoryDTOs() != null) {
            TopGithubReposDTO body = responseEntity.getBody();
            List<GithubRepositoryDto> githubRepositoryDtos = body.githubRepositoryDTOs();

            for (GithubRepositoryDto githubRepositoryDto : githubRepositoryDtos) {
                if (!StringUtils.equalsIgnoreCase(githubRepositoryDto.language(), languageFilter)) {
                    throw new IllegalArgumentException("Github returned repositories where project language is not" +
                            " filter language. Most probably passed language doesn't exist in GitHub.");
                }
            }
        }
    }

}
