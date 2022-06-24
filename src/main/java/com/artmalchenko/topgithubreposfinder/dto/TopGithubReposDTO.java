package com.artmalchenko.topgithubreposfinder.dto;

import com.artmalchenko.topgithubreposfinder.dto.repository.GithubRepositoryDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TopGithubReposDTO(
        @JsonProperty("items") List<GithubRepositoryDto> githubRepositoryDTOs) {
}