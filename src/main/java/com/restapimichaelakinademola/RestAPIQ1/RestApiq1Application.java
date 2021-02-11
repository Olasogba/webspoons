package com.restapimichaelakinademola.RestAPIQ1;

import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapimichaelakinademola.RestAPIQ1.DTOs.SnippetResponseDTO;
import com.restapimichaelakinademola.RestAPIQ1.config.HelperUtils;
import com.restapimichaelakinademola.RestAPIQ1.controllers.SnippetRestController;
import com.restapimichaelakinademola.RestAPIQ1.entities.Snippet;
import com.restapimichaelakinademola.RestAPIQ1.repositories.SnippetRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.modelmapper.ModelMapper;


@SpringBootApplication(scanBasePackages = {
    "com.restapimichaelakinademola.RestAPIQ1.repositories",
    "com.restapimichaelakinademola.RestAPIQ1.config",
    "com.restapimichaelakinademola.RestAPIQ1.controllers",
    "com.restapimichaelakinademola.RestAPIQ1.DTOs",
    "com.restapimichaelakinademola.RestAPIQ1.entities",
    "com.restapimichaelakinademola.RestAPIQ1.exception;",
    "com.restapimichaelakinademola.RestAPIQ1.services"
})
public class RestApiq1Application {

    public static void main(String[] args) {
            SpringApplication app = new SpringApplication(RestApiq1Application.class);
            app.setDefaultProperties(Collections
            .singletonMap("server.port", "8083"));
            app.run(args);
            // SpringApplication.run(RestApiq1Application.class, args);
    }
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper _mapper = new ModelMapper();
        _mapper.typeMap(Snippet.class, SnippetResponseDTO.class);
        return _mapper;
    }
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper _oMapper = new ObjectMapper();
        return _oMapper;
    }
    
    @Bean
    public HelperUtils helperClass() {
        return new HelperUtils();
    }

    @Bean
    public SnippetRepository snippetRepository() {
        return new SnippetRepository();
    }
}
