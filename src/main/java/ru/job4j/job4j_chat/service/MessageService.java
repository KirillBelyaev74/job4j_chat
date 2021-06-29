package ru.job4j.job4j_chat.service;

import org.springframework.stereotype.Service;
import ru.job4j.job4j_chat.model.Message;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final List<Message> messages = new ArrayList<>();

    public List<Message> findAll() {
        return messages;
    }

    public Message save(Message message) {
        messages.add(message);
        return message;
    }
}