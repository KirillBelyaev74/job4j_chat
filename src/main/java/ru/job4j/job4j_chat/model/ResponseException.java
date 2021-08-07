package ru.job4j.job4j_chat.model;

public class ResponseException {

    private String message;

    public ResponseException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseException { "
                + "message = '" + message + '\''
                + '}';
    }
}
