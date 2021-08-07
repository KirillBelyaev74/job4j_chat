package ru.job4j.job4j_chat.exceptionHandler;

import org.springframework.stereotype.Component;

@Component
public class Validate {

    public void validateID(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Значение ID должно быть больше нуля. Ваше ID = " + id);
        }
    }

    public <T> void validateObjectNull(T t, int id) {
        if (t == null) {
            throw new NullPointerException("Пользователя с ID = " + id + " не существует");
        }
    }
}
