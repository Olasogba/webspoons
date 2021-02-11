package com.restapimichaelakinademola.RestAPIQ1.entities;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Snippet {    
    public String name;
    
    public int expires_in;

    public String snippet;

    public int likes;

    private Instant expires_at;
}
