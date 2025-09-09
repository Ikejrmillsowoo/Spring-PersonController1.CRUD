package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired
    PersonRepository repository;

    @PostMapping
    ResponseEntity<Person> createPerson(@RequestBody Person p){
        Person newPerson = repository.save(p);

        return new ResponseEntity(newPerson, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<Person> getPerson(@PathVariable int id){
        Person p = repository.findOne(id);
       return new ResponseEntity(p, HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<List<Person>> getPersonList(){
        List<Person> list = (List<Person>) repository.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    Person updatePerson(@RequestBody Person p){
       Person oldPerson = getPerson(p.getId()).getBody();
       if (oldPerson!= null){
           oldPerson.setFirstName(p.getFirstName());
           oldPerson.setLastName(p.getLastName());
       } else {
          oldPerson = createPerson(p).getBody();
       }
       repository.save(oldPerson);
        return oldPerson;
    }

    @DeleteMapping("/{id}")
    void deletePerson(@PathVariable int id){
    Person personToDelete = getPerson(id).getBody();
    repository.delete(personToDelete);
    }


}
