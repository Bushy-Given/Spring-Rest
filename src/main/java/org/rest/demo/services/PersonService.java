package org.rest.demo.services;

import org.rest.demo.entities.Person;
import org.rest.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;
    @Autowired
    public  PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Iterable<Person> getPersons(){
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id){
        return personRepository.findById(id);
    }

    public Iterable<Person> addPersons(Iterable<Person> persons){
        return personRepository.saveAll(persons);
    }

    public Person addPerson(Person person){
        return personRepository.save(person);
    }

    public  Person updatePerson(Person person){
        return personRepository.save(person);
    }

    public void deletePerson(Person person){
        personRepository.delete(person);
    }

    public void deleteAllPersons(){
        personRepository.deleteAll();
    }
}
