package com.restapimichaelakinademola.RestAPIQ1.services.interfaces;

import com.restapimichaelakinademola.RestAPIQ1.DTOs.SnippetRequestDTO;
import com.restapimichaelakinademola.RestAPIQ1.DTOs.SnippetResponseDTO;
import com.restapimichaelakinademola.RestAPIQ1.entities.Snippet;

import org.springframework.http.ResponseEntity;

public interface SnippetService {
    ResponseEntity<SnippetResponseDTO> saveSnippet(SnippetRequestDTO snippet);

    ResponseEntity<SnippetResponseDTO> getSnippet(String name);

    ResponseEntity<SnippetResponseDTO> likeSnippet(String name);
}
