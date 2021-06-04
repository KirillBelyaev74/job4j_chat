package ru.job4j.job4j_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_chat.model.Person;
import ru.job4j.job4j_chat.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        return new ResponseEntity<>(
                personService.saveOrUpdate(person), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        return new ResponseEntity<>(
                personService.saveOrUpdate(person), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Person> getAllPerson() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        Person person = personService.findById(id);
        return person == null
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        personService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}