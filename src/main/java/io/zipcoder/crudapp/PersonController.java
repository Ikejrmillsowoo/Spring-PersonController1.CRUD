package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired
    PersonRepository repository;

    @PostMapping
    Person createPerson(@RequestBody Person p){
        Person newPerson = repository.save(p);
        return newPerson;
    }
    @GetMapping("/{id}")
    Person getPerson(@PathVariable int id){
       return repository.findOne(id);
    }
    @GetMapping
    List<Person> getPersonList(){
        return (List<Person>) repository.findAll();
    }
    @PutMapping("/{id}")
    Person updatePerson(@RequestBody Person p){
       Person oldPerson =  getPerson(p.getId());
       if (oldPerson!= null){
           oldPerson.setFirstName(p.getFirstName());
           oldPerson.setLastName(p.getLastName());
       } else {
          oldPerson =  createPerson(p);
       }
       repository.save(oldPerson);
        return oldPerson;
    }

    @DeleteMapping("/{id}")
    void deletePerson(@PathVariable int id){
    Person personToDelete = getPerson(id);
    repository.delete(personToDelete);
    }


}
