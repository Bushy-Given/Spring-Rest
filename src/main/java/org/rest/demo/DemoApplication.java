package org.rest.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.rest.demo.entities.Person;
import org.rest.demo.services.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;


@SpringBootApplication
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
    @Bean
	CommandLineRunner commandLineRunner(PersonService personService){
         return  (String... args) -> {
         	//read json and write to db
			 ObjectMapper objectMapper = new ObjectMapper();
			 TypeReference<Iterable<Person>> typeReference = new TypeReference<Iterable<Person>>() {} ;
			 InputStream inputStream = TypeReference.class.getResourceAsStream("/json/person.json");
			 try {
			 	 Iterable<Person> persons = objectMapper.readValue(inputStream,typeReference);
			 	 personService.addPersons(persons);
				 System.out.println("persons saved!!!");

			 }catch (IOException e){
				 System.out.println("Unable to save Persons :" + e.getMessage());
			 }

		 };

	}
}
