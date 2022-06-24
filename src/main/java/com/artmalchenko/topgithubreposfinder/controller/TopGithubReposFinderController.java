package com.artmalchenko.topgithubreposfinder.controller;

import com.artmalchenko.topgithubreposfinder.controller.api.TopGithubReposFinderControllerAPI;
import com.artmalchenko.topgithubreposfinder.dto.TopGithubReposDTO;
import com.artmalchenko.topgithubreposfinder.service.TopGithubReposFinderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/topRepositories")
public class TopGithubReposFinderController implements TopGithubReposFinderControllerAPI {

    private final TopGithubReposFinderService topGithubReposFinderService;

    public TopGithubReposFinderController(TopGithubReposFinderService topGithubReposFinderService) {
        this.topGithubReposFinderService = topGithubReposFinderService;
    }

    @Override
    @GetMapping
    public ResponseEntity<TopGithubReposDTO> getTopRepositories(
            @RequestParam(name = "createdOnOrAfter", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdOnOrAfter,

            @RequestParam(name = "language", required = false) String programmingLanguage,
            @RequestParam(name = "limit", required = false) Integer limit) {
        return topGithubReposFinderService.searchTopGithubRepos(createdOnOrAfter, programmingLanguage, limit);
    }

}
