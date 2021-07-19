package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {    //we're using interface so that we can have any implementation to our database.
                                // also we use dependency injections to switch between our implementations.
    int insertPerson(UUID id, Person person);   // so we use method insert-person to insert person with given id to database.

    default int insertPerson(Person person){    // we used default method to add person to database without id
        UUID id = UUID.randomUUID();         // it will generate id randomly
        return insertPerson(id,person);
    }
    // defining the functionality to retrieve people form our database
    List<Person> selectAllPeople();   //this will return list of people to our database

    Optional<Person> selectPersonByID(UUID id);

    int deletePersonByID(UUID id);    // to delete person by id from our database

    int updatePersonByID(UUID id, Person person);   //to update person id in our database.


}

