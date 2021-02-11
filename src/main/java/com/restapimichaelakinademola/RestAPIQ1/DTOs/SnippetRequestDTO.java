package com.restapimichaelakinademola.RestAPIQ1.DTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SnippetRequestDTO {
    @NotBlank(message = "Name is mandatory")
    public String name;
    
    @NotNull(message = "Expiry time is mandatory")
    public int expires_in;

    @NotBlank(message = "Snippet is mandatory")
    public String snippet;
}
