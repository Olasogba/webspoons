package com.restapimichaelakinademola.RestAPIQ1.services.implementation;

import java.net.URI;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.restapimichaelakinademola.RestAPIQ1.DTOs.SnippetRequestDTO;
import com.restapimichaelakinademola.RestAPIQ1.DTOs.SnippetResponseDTO;
import com.restapimichaelakinademola.RestAPIQ1.config.HelperUtils;
import com.restapimichaelakinademola.RestAPIQ1.entities.Snippet;
import com.restapimichaelakinademola.RestAPIQ1.exception.EntityNotFoundException;
import com.restapimichaelakinademola.RestAPIQ1.repositories.SnippetRepository;
import com.restapimichaelakinademola.RestAPIQ1.services.interfaces.SnippetService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SnippetServiceImpl implements SnippetService {

    @Autowired
    private final SnippetRepository _snippetRepository;
    
    @Autowired
    private final ModelMapper _modelMapper;
    
    @Autowired
    private final HelperUtils _helper;

    private @Autowired HttpServletRequest request;

    public ResponseEntity<SnippetResponseDTO> saveSnippet(SnippetRequestDTO snippet) {
        Snippet snip = _snippetRepository.getInstance();
        _modelMapper.map(snippet, snip);

        // set expiry time
        snip.setExpires_at(Instant.now().plusSeconds(snippet.expires_in));

        // add & save
        _snippetRepository.addSnippet(snip);

        // return
        SnippetResponseDTO snippetToReturn = _modelMapper.map(snip, SnippetResponseDTO.class);
        URI location = _helper.createURI("/"+snip.getName());
        snippetToReturn.url = request.getRequestURL().toString()+"/"+snip.getName();
        
        return ResponseEntity.created(location).body(snippetToReturn);
    }

    
    
    public ResponseEntity<SnippetResponseDTO> getSnippet(String name) {

        // get a snippet with a name
        Snippet snip = _snippetRepository.getSnippet(name);
        
        // if snippet doesn't exist, return 404
        if(snip==null) throw new EntityNotFoundException(Snippet.class, "name", name);

        // if snippet is expired, return 404
        if(snippetIsExpired(snip)) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);       
          
        // else increase snippet life by 30 secs
        updateExpiryTime(snip);

        SnippetResponseDTO snippetToReturn = _modelMapper.map(snip, SnippetResponseDTO.class);
        return ResponseEntity.ok(snippetToReturn);
    }

    
    
    public ResponseEntity<SnippetResponseDTO> likeSnippet(String name) {
        Snippet snip = _snippetRepository.getSnippet(name);

        if(snip==null) throw new EntityNotFoundException(Snippet.class, "name", name);

        snip.likes++;

        // update expiry
        updateExpiryTime(snip);

        SnippetResponseDTO snippetToReturn = _modelMapper.map(snip, SnippetResponseDTO.class);
        return ResponseEntity.ok(snippetToReturn);
    }

    private boolean snippetIsExpired(Snippet snip) {
        Instant now = Instant.now();
        return now.compareTo(snip.getExpires_at()) > 0;
    }

    private void updateExpiryTime(Snippet snip) {
        snip.expires_in+=30;
        snip.setExpires_at(snip.getExpires_at().plusSeconds(30));
    }
}
