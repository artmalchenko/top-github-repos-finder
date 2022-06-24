package com.artmalchenko.topgithubreposfinder.integration;

import com.artmalchenko.topgithubreposfinder.dto.TopGithubReposDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class TopGithubReposFinderIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFiltersAppliedCorrectly() throws Exception {
        String language = "java";
        int limit = 100;
        String createdAfter = "2019-01-10";

        MvcResult mvcResult = mockMvc.perform(get(String.format("/topRepositories?language=%s&limit=%d&createdOnOrAfter=%s",
                language, limit, createdAfter))
                .contentType("application/json")).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        TopGithubReposDTO topGithubReposDTO = objectMapper.readValue(response, TopGithubReposDTO.class);

        assert languageFilterApplied(topGithubReposDTO, language);
        assert limitFilterApplied(topGithubReposDTO, limit);
        assert createdAfterFilterApplied(topGithubReposDTO, createdAfter);
    }

    private boolean languageFilterApplied(TopGithubReposDTO topGithubReposDTO, String language) {
        return topGithubReposDTO.githubRepositoryDTOs().stream()
                .allMatch(value -> StringUtils.equalsIgnoreCase(language, value.language()));
    }

    private boolean limitFilterApplied(TopGithubReposDTO topGithubReposDTO, int limit) {
        return topGithubReposDTO.githubRepositoryDTOs().size() == limit;
    }

    private boolean createdAfterFilterApplied(TopGithubReposDTO topGithubReposDTO, String createdAfterStr) {
        return topGithubReposDTO.githubRepositoryDTOs().stream().allMatch(repository -> {
            LocalDate repositoryCreatedDate = repository.createdAt().toLocalDate();
            LocalDate createdAfterFilterDate = LocalDate.parse(createdAfterStr);
            return repositoryCreatedDate.equals(createdAfterFilterDate) || repositoryCreatedDate.isAfter(createdAfterFilterDate);
        });
    }
}
