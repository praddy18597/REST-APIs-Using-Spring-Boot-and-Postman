package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")   //defining the path from a postman
@RestController  //this is now RestController class which have http methods and expose endpoints that client can consume
public class PersonController {

    private final PersonService personService;   //provide reference to actual service

    @Autowired  // we are injecting PersonService into constructor PersonController
    public PersonController(PersonService personService) {
        this.personService = personService;       // adding a constructor to initialize object of variable
    }

    @PostMapping  // this tell to spring that this method will be used as Post request
    public void addPerson(@Validated @NonNull @RequestBody Person person){  // we're receiving this payload from actual body in simple word we converting Json object into person.
        personService.addPerson(person);       // to add person we use addPerson method
    }
    @GetMapping    //this method will serve as Get request
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }
    @GetMapping(path = "{id}")   // we want id in path that's why we put in Annotation
    public Person getPersonById(@PathVariable("id") UUID id) {  // we can get id from path using @PathVariable annotation
        return personService.getPersonById(id)
                .orElse(null); // if you don't find anything return null
    }
    @DeleteMapping(path = "{id}")  // this method will serve as a Delete request
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }
    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Validated @NonNull @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }

}

// Get: is used for retrieving the data from your server
// Post: adding resource to your server
// Put : Modifying your server
// Delete : Deleting a resource