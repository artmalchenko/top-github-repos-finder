package com.artmalchenko.topgithubreposfinder.dto.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

public record License(
        @JsonProperty("name")
        String name,

        @JsonProperty("spdx_id")
        String spdxId,

        @JsonProperty("key")
        String key,

        @JsonProperty("url")
        String url,

        @JsonProperty("node_id")
        String nodeId
) {
}