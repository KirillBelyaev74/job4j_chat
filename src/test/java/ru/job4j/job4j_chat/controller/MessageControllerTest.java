package ru.job4j.job4j_chat.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.job4j.job4j_chat.model.Message;
import ru.job4j.job4j_chat.model.Person;
import ru.job4j.job4j_chat.model.Role;
import ru.job4j.job4j_chat.model.Room;
import ru.job4j.job4j_chat.repository.PersonRepository;
import ru.job4j.job4j_chat.repository.RoomRepository;
import ru.job4j.job4j_chat.service.MessageService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class MessageControllerTest {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MessageService messageService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final Room room = new Room("one");
    private final Person person = new Person("kirill", "kirill");
    private final Message message = new Message("Hello");

    @Before
    public void start() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        roomRepository.deleteAll();
        personRepository.deleteAll();

        roomRepository.save(room);
        person.setRole(new Role("user"));
        personRepository.save(person);

        message.setRoom(room);
        message.setPerson(person);
        messageService.save(message);
    }

    @Test
    public void whenAddMessage() throws Exception {
        mockMvc.perform(post("/message/{idP}/{idR}", person.getId(), room.getId())
                .contentType("application/json")
                .content("{ \"message\": \"goodbye\" }"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("message").value("goodbye"))
                .andExpect(jsonPath("person.login").value("kirill"));
    }

    @Test
    public void whenGetAllMessages() throws Exception {
        mockMvc.perform(get("/message/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].message").value("Hello"))
                .andExpect(jsonPath("$.[0].person.login").value("kirill"));
    }

    @Test
    public void whenGetAllPersons() throws Exception {
        mockMvc.perform(get("/message/person"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].login").value("kirill"));
    }

    @Test
    public void whenGetAllRooms() throws Exception {
        mockMvc.perform(get("/message/room"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].name").value("one"));
    }
}