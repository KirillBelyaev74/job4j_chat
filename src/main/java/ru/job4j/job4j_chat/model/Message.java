package ru.job4j.job4j_chat.model;

import java.util.Date;
import java.util.Objects;

public class Message {

    private String message;

    private Date created;

    private Person person;

    private Room room;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
        this.created = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message1 = (Message) o;
        return Objects.equals(message, message1.message)
                && Objects.equals(created, message1.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, created);
    }

    @Override
    public String toString() {
        return "Message { "
                + "message = '" + message + '\''
                + ", created = " + created
                + '}';
    }
}
