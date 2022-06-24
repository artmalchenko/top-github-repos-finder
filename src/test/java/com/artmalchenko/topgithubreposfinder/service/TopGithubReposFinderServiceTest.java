package com.artmalchenko.topgithubreposfinder.service;

import com.artmalchenko.topgithubreposfinder.dto.TopGithubReposDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TopGithubReposFinderServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TopGithubReposFinderService topGithubReposFinderService;

    @Captor
    private ArgumentCaptor<RequestEntity<Void>> requestEntityCaptor;

    @Test
    @DisplayName("Verify query when passing only createdAtOrAfter filter.")
    void searchTopGithubRepos_passCreatedAtOrAfterFilter_verifyQuery() {
        //arrange
        LocalDate createdAfterFilterDate = LocalDate.parse("2019-01-10");

        //act
        topGithubReposFinderService.searchTopGithubRepos(createdAfterFilterDate, null, null);

        //assert
        verify(restTemplate).exchange(requestEntityCaptor.capture(), eq(TopGithubReposDTO.class));
        RequestEntity<Void> requestEntity = requestEntityCaptor.getValue();
        URI url = requestEntity.getUrl();

        String query = URLDecoder.decode(url.getQuery(), StandardCharsets.UTF_8);
        assertTrue(query.contains("created:>=" + createdAfterFilterDate.toString()));
    }

    @Test
    @DisplayName("Verify query when passing only language filter.")
    void searchTopGithubRepos_passLanguageFilter_verifyQuery() {
        //arrange
        String languageFilter = "java";

        //act
        topGithubReposFinderService.searchTopGithubRepos(null, languageFilter, null);

        //assert
        verify(restTemplate).exchange(requestEntityCaptor.capture(), eq(TopGithubReposDTO.class));
        RequestEntity<Void> requestEntity = requestEntityCaptor.getValue();
        URI url = requestEntity.getUrl();

        String query = URLDecoder.decode(url.getQuery(), StandardCharsets.UTF_8);
        assertTrue(query.contains("language:" + languageFilter));
    }

    @Test
    @DisplayName("Verify query when passing only limit filter.")
    void searchTopGithubRepos_passLimitFilter_verifyQuery() {
        //arrange
        int limitFilter = 100;

        //act
        topGithubReposFinderService.searchTopGithubRepos(null, null, limitFilter);

        //assert
        verify(restTemplate).exchange(requestEntityCaptor.capture(), eq(TopGithubReposDTO.class));
        RequestEntity<Void> requestEntity = requestEntityCaptor.getValue();
        URI url = requestEntity.getUrl();

        String query = URLDecoder.decode(url.getQuery(), StandardCharsets.UTF_8);
        assertTrue(query.contains("per_page=" + limitFilter));
    }

    @Test
    @DisplayName("Verify query when passing all three filters.")
    void searchTopGithubRepos_passAllThreeFilters_verifyQuery() {
        //arrange
        LocalDate createdAfterFilterDate = LocalDate.parse("2019-01-10");
        String languageFilter = "java";
        int limitFilter = 100;

        //act
        topGithubReposFinderService.searchTopGithubRepos(createdAfterFilterDate, languageFilter, limitFilter);

        //assert
        verify(restTemplate).exchange(requestEntityCaptor.capture(), eq(TopGithubReposDTO.class));
        RequestEntity<Void> requestEntity = requestEntityCaptor.getValue();
        URI url = requestEntity.getUrl();

        String query = URLDecoder.decode(url.getQuery(), StandardCharsets.UTF_8);
        assertTrue(query.contains("language:" + languageFilter));
        assertTrue(query.contains("per_page=" + limitFilter));
        assertTrue(query.contains("created:>=" + createdAfterFilterDate.toString()));
    }

    @Test
    @DisplayName("Verify IllegalArgumentException is thrown when passing invalid limit filter.")
    void searchTopGithubRepos_passNotAllowedLimitFilter_expectException() {
        //arrange
        int limitFilter = 11;

        //act & assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            topGithubReposFinderService.searchTopGithubRepos(null, null, limitFilter);
        });
    }

    @Test
    @DisplayName("Verify IllegalArgumentException is thrown when passing invalid createdOnOrAfter filter.")
    void searchTopGithubRepos_passNotAllowedDateFilter_expectException() {
        //arrange
        LocalDate createdOnOrAfterFilter = LocalDate.parse("2099-01-10");

        //act & assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            topGithubReposFinderService.searchTopGithubRepos(createdOnOrAfterFilter, null, null);
        });
    }

}