package org.rest.demo.controllers;

import org.rest.demo.entities.Person;
import org.rest.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/")
public class PersonController {

    private PersonService personService;
    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("persons")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Person> persons(){
        return personService.getPersons();
    }

    @GetMapping("person/{id}")
    public Optional<Person> person(@PathVariable Long id){
        return personService.getPersonById(id);
    }

    @PostMapping("persons")
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Person> persons(@RequestBody Iterable<Person> persons){
        return personService.addPersons(persons);
    }

    @PostMapping("person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person person(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @PutMapping("person/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Person updatePerson(@PathVariable Long id,@RequestBody Person person){
        person.setId(id);
        return  personService.updatePerson(person);
    }

    @DeleteMapping("person/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable Long id, Person person){
        person.setId(id);
        personService.deletePerson(person);
    }

    @DeleteMapping("persons")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(){
        personService.deleteAllPersons();
    }
}
