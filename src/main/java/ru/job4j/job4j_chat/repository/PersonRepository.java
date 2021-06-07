package ru.job4j.job4j_chat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.job4j_chat.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Override
    List<Person> findAll();

    Person findByLogin(String login);
}
