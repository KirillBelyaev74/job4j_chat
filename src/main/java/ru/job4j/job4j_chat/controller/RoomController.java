package ru.job4j.job4j_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_chat.model.Room;
import ru.job4j.job4j_chat.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/")
    public ResponseEntity<Room> saveRoom(@RequestBody Room room) {
        return new ResponseEntity<>(
                roomService.saveOrUpdate(room), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Room> updateRoomById(@RequestBody Room room) {
        return new ResponseEntity<>(
                roomService.saveOrUpdate(room), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable int id) {
        Room room = roomService.findById(id);
        return room == null
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(room, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Room> deleteRoomById(@PathVariable int id) {
        roomService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}