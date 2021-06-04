package ru.job4j.job4j_chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_chat.model.Room;
import ru.job4j.job4j_chat.repository.RoomRepository;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public Room saveOrUpdate(Room room) {
        return repository.save(room);
    }

    public List<Room> findAll() {
        return repository.findAll();
    }

    public Room findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
