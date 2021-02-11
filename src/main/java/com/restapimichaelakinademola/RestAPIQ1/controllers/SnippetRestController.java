package com.restapimichaelakinademola.RestAPIQ1.controllers;

import javax.validation.Valid;

import com.restapimichaelakinademola.RestAPIQ1.DTOs.SnippetRequestDTO;
import com.restapimichaelakinademola.RestAPIQ1.DTOs.SnippetResponseDTO;
import com.restapimichaelakinademola.RestAPIQ1.services.interfaces.SnippetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnippetRestController.ENDPOINT)
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = "Snippet")
@RequiredArgsConstructor
public class SnippetRestController {    
    public static final String ENDPOINT = "/snippets";
    public static final String ENDPOINT_ID = "/{name}";
    public static final String PATH_VARIABLE_ID = "name";

    private static final String API_PARAM_ID = "SNIPPET_NAME";
    
    @Autowired
    private SnippetService snippetService;
    
    // save a snippet
    // return the createdAtRoute url for the snippet
    @ApiOperation("Get a Snippet")
    @GetMapping(ENDPOINT_ID)
    public ResponseEntity<SnippetResponseDTO> getSnippet(
                    @ApiParam(name = API_PARAM_ID, required = true)
                    @PathVariable(PATH_VARIABLE_ID)
                    final String snippet
    ) { 
            return snippetService.getSnippet(snippet);
    }

    
    @ApiOperation("Create a Snippet")
    @PostMapping()
    public ResponseEntity<SnippetResponseDTO> createSnippet(
            @ApiParam(name = API_PARAM_ID, required = true)
            @Valid @RequestBody SnippetRequestDTO snippet) {
        System.out.print(this.snippetService.toString());
        return snippetService.saveSnippet(snippet);
    }


    @ApiOperation("Like a snippet")
    @GetMapping(value = "like/"+ENDPOINT_ID)
    public ResponseEntity<SnippetResponseDTO> likeSnippet(
                                                 @PathVariable(PATH_VARIABLE_ID) String snippet) {
        return snippetService.likeSnippet(snippet);
    }
}
