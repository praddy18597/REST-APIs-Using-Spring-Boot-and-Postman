package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class Person {
    private final UUID id;                 // initializing two variables id and name
    @NonNull   // if you have empty string it's not null it's actually blank
    private final String name;

    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {    // initializing constructor to initializing an object
        // we used @JsonProperty for defining the Properties
        this.id = id;                        // this is used for initializing current intance of class
        this.name = name;
    }

    public UUID getId() {                   // we used get method for both id and name
        return id;
    }

    public String getName() {
        return name;
    }
}
