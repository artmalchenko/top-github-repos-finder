package com.artmalchenko.topgithubreposfinder.controller.api;

import com.artmalchenko.topgithubreposfinder.dto.TopGithubReposDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@OpenAPIDefinition(info = @Info(title = "Top Github Repositories Finder API"))
public interface TopGithubReposFinderControllerAPI {

    @Operation(summary = "Find top Github repositories with date, language and limit filters.")
    ResponseEntity<TopGithubReposDTO> getTopRepositories(
            @Parameter(description = "Repositories which are created on this date or after will be returned. " +
                    "Format: yyyy-MM-dd.", example = "2019-01-10")
            LocalDate filterDate,

            @Parameter(description = "Repositories with the specified language as main one will be returned.",
                    example = "java")
            String programmingLanguage,

            @Parameter(description = "Limits the number of repositories returned to a user. Allowed values: 10, 50, 100." +
                    " If you don't specify this parameter you'll get default Github Search API value of 30 records.",
                    example = "10")
            Integer limit
    );

}
