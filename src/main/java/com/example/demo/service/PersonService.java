package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service   //so that people can know this is an actual service
public class PersonService {    // we defining our actual service

    private final PersonDao personDao;      //we create final variable personDao for reference to method addPerson down below

    @Autowired  //we are injecting PersonDao into constructor PersonService
    // we have multiple implementations of PersonDao interface to distinguish between them we use @qualifier
    // also from @qualifier you can do multiple implementations like "fakeDao"
    public PersonService(@Qualifier("postgres") PersonDao personDao) {  // create constructor initialize the personDao object
        this.personDao = personDao;
    }

    public int addPerson(Person person){       // we adding person to database
        return personDao.insertPerson(person);   // we don't passing id to this method randomly generated
    }

    public List<Person> getAllPeople() {      // method for listing the people from our database
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {    // method will search the person by its given id
        return personDao.selectPersonByID(id);
    }

    public int deletePerson(UUID id) {              // method for deleting the person by it's id
        return personDao.deletePersonByID(id);
    }

    public int updatePerson(UUID id, Person newPerson){  // method for update person by it's id and new person
        return personDao.updatePersonByID(id, newPerson);
    }

}

// Note: PersonDao datatype will not be run since it's not instantiated. that's why we use annotations
// to instantiated beans