package com.artmalchenko.topgithubreposfinder.dto.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record GithubRepositoryDto(
        @JsonProperty("allow_forking")
        Boolean allowForking,

        @JsonProperty("stargazers_count")
        Integer stargazersCount,

        @JsonProperty("is_template")
        Boolean isTemplate,

        @JsonProperty("pushed_at")
        String pushedAt,

        @JsonProperty("subscription_url")
        String subscriptionUrl,

        @JsonProperty("language")
        String language,

        @JsonProperty("branches_url")
        String branchesUrl,

        @JsonProperty("issue_comment_url")
        String issueCommentUrl,

        @JsonProperty("labels_url")
        String labelsUrl,

        @JsonProperty("score")
        Double score,

        @JsonProperty("subscribers_url")
        String subscribersUrl,

        @JsonProperty("releases_url")
        String releasesUrl,

        @JsonProperty("svn_url")
        String svnUrl,

        @JsonProperty("id")
        Integer id,

        @JsonProperty("forks")
        Integer forks,

        @JsonProperty("archive_url")
        String archiveUrl,

        @JsonProperty("git_refs_url")
        String gitRefsUrl,

        @JsonProperty("forks_url")
        String forksUrl,

        @JsonProperty("visibility")
        String visibility,

        @JsonProperty("statuses_url")
        String statusesUrl,

        @JsonProperty("ssh_url")
        String sshUrl,

        @JsonProperty("license")
        License license,

        @JsonProperty("full_name")
        String fullName,

        @JsonProperty("size")
        Integer size,

        @JsonProperty("languages_url")
        String languagesUrl,

        @JsonProperty("html_url")
        String htmlUrl,

        @JsonProperty("collaborators_url")
        String collaboratorsUrl,

        @JsonProperty("clone_url")
        String cloneUrl,

        @JsonProperty("name")
        String name,

        @JsonProperty("pulls_url")
        String pullsUrl,

        @JsonProperty("default_branch")
        String defaultBranch,

        @JsonProperty("hooks_url")
        String hooksUrl,

        @JsonProperty("trees_url")
        String treesUrl,

        @JsonProperty("tags_url")
        String tagsUrl,

        @JsonProperty("jsonMemberPrivate")
        Boolean jsonMemberPrivate,

        @JsonProperty("contributors_url")
        String contributorsUrl,

        @JsonProperty("has_downloads")
        Boolean hasDownloads,

        @JsonProperty("notifications_url")
        String notificationsUrl,

        @JsonProperty("open_issues_count")
        Integer openIssuesCount,

        @JsonProperty("description")
        String description,

        @JsonProperty("created_at")
        LocalDateTime createdAt,

        @JsonProperty("watchers")
        Integer watchers,

        @JsonProperty("keys_url")
        String keysUrl,

        @JsonProperty("deployments_url")
        String deploymentsUrl,

        @JsonProperty("has_projects")
        Boolean hasProjects,

        @JsonProperty("archived")
        Boolean archived,

        @JsonProperty("has_wiki")
        Boolean hasWiki,

        @JsonProperty("updated_at")
        String updatedAt,

        @JsonProperty("comments_url")
        String commentsUrl,

        @JsonProperty("stargazers_url")
        String stargazersUrl,

        @JsonProperty("disabled")
        Boolean disabled,

        @JsonProperty("git_url")
        String gitUrl,

        @JsonProperty("has_pages")
        Boolean hasPages,

        @JsonProperty("owner")
        Owner owner,

        @JsonProperty("commits_url")
        String commitsUrl,

        @JsonProperty("compare_url")
        String compareUrl,

        @JsonProperty("git_commits_url")
        String gitCommitsUrl,

        @JsonProperty("topics")
        List<String> topics,

        @JsonProperty("blobs_url")
        String blobsUrl,

        @JsonProperty("git_tags_url")
        String gitTagsUrl,

        @JsonProperty("merges_url")
        String mergesUrl,

        @JsonProperty("downloads_url")
        String downloadsUrl,

        @JsonProperty("has_issues")
        Boolean hasIssues,

        @JsonProperty("url")
        String url,

        @JsonProperty("contents_url")
        String contentsUrl,

        @JsonProperty("mirror_url")
        Object mirrorUrl,

        @JsonProperty("milestones_url")
        String milestonesUrl,

        @JsonProperty("teams_url")
        String teamsUrl,

        @JsonProperty("fork")
        Boolean fork,

        @JsonProperty("issues_url")
        String issuesUrl,

        @JsonProperty("events_url")
        String eventsUrl,

        @JsonProperty("issue_events_url")
        String issueEventsUrl,

        @JsonProperty("assignees_url")
        String assigneesUrl,

        @JsonProperty("open_issues")
        Integer openIssues,

        @JsonProperty("watchers_count")
        Integer watchersCount,

        @JsonProperty("node_id")
        String nodeId,

        @JsonProperty("homepage")
        String homepage,

        @JsonProperty("forks_count")
        Integer forksCount
) {
}