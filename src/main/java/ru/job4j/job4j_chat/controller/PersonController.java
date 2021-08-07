package ru.job4j.job4j_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.job4j_chat.exceptionHandler.Validate;
import ru.job4j.job4j_chat.model.Person;
import ru.job4j.job4j_chat.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final BCryptPasswordEncoder encoder;
    private final Validate validate;

    @Autowired
    public PersonController(PersonService personService, BCryptPasswordEncoder encoder, Validate validate) {
        this.personService = personService;
        this.encoder = encoder;
        this.validate = validate;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
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
        validate.validateID(id);
        Person person = personService.findById(id);
        validate.validateObjectNull(person, id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        validate.validateID(id);
        personService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}