package ru.job4j.job4j_chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_chat.model.Person;
import ru.job4j.job4j_chat.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person saveOrUpdate(Person person) {
        return repository.save(person);
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
