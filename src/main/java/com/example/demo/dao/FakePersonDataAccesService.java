package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository("fakeDao") //this class need to be instantiated as a beam so that we can inject in all the classes
public class FakePersonDataAccesService implements PersonDao{  // to implement personDao interface we use class fakeperson

    // define our list
    private static List<Person> DB = new ArrayList<Person>();

    @Override
    public int insertPerson(UUID id, Person person) {  // implement this method from personDao class
        DB.add(new Person(id, person.getName()));    // adding the person into database db
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonByID(UUID id) { //this method will check whether we have person with given id
        return DB.stream()    //for above purpose we stream our database
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonByID(UUID id) {  // if person is exists in database delete the person otherwise return zero
        Optional<Person> personMaybe = selectPersonByID(id);
        if (personMaybe.isPresent()) {
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonByID(UUID id, Person update) {
        return selectPersonByID(id)
                .map(person -> {
                    int indexOfPersonToUpdate = DB.indexOf(person);
                    if (indexOfPersonToUpdate >= 0) {
                        DB.set(indexOfPersonToUpdate, new Person(id, update.getName()));
                        return 1;
                    }
                    return  0;
                } )
                .orElse(0);
    }
}
