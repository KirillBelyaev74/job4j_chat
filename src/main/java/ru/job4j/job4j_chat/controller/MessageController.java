package ru.job4j.job4j_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_chat.model.Message;
import ru.job4j.job4j_chat.model.Person;
import ru.job4j.job4j_chat.model.Room;
import ru.job4j.job4j_chat.service.MessageService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final RestTemplate restTemplate;
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService, RestTemplate restTemplate) {
        this.messageService = messageService;
        this.restTemplate = restTemplate;
    }

    private static final String API_PERSON = "http://localhost:8080/person/";
    private static final String API_PERSON_ID = "http://localhost:8080/person/{id}";
    private static final String API_ROOM = "http://localhost:8080/room/";
    private static final String API_ROOM_ID = "http://localhost:8080/room/{id}";

    @GetMapping("/")
    public List<Message> getAllMessage() {
        return messageService.findAll();
    }

    @GetMapping("/person")
    public List<Person> getAllPerson() {
        return restTemplate.exchange(
                API_PERSON,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() { }
        ).getBody();
    }

    @GetMapping("/room")
    public List<Room> getAllRoom() {
        return restTemplate.exchange(
                API_ROOM,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Room>>() { }
        ).getBody();
    }

    @PostMapping("/{idP}/{idR}")
    public ResponseEntity<Message> saveMessage(
            @PathVariable int idP, @PathVariable int idR, @RequestBody Message message) {
        Person person = restTemplate.getForObject(API_PERSON_ID, Person.class, idP);
        Room room = restTemplate.getForObject(API_ROOM_ID, Room.class, idR);
        message.setPerson(person);
        message.setRoom(room);
        message.setCreated(new Date());
        messageService.save(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}