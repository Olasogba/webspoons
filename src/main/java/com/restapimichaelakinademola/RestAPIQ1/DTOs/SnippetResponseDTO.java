package com.restapimichaelakinademola.RestAPIQ1.DTOs;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnippetResponseDTO {
    public String name;

    public String snippet;

    public int likes;

    public String url;

    private String expires_at;
}
