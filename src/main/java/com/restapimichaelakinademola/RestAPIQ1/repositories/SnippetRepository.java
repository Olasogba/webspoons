package com.restapimichaelakinademola.RestAPIQ1.repositories;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.restapimichaelakinademola.RestAPIQ1.entities.Snippet;

public class SnippetRepository {
    // initialize a dictionary here, this will store the snippets
    // make this class available as a singleton
    private static HashMap<String, Snippet> _data = new HashMap<String, Snippet>();

    public Snippet addSnippet(Snippet snippet)
    {
        _data.put(snippet.name, snippet);
        return snippet;
    }


    public Snippet getSnippet(String name)
    {
        try {
            return _data.get(name);
        } catch (Exception e) {
            return null;
        }
    }

    public Snippet getInstance() {
        return new Snippet();
    }
}
