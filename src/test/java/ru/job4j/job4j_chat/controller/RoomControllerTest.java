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
import ru.job4j.job4j_chat.model.Room;
import ru.job4j.job4j_chat.repository.RoomRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class RoomControllerTest {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final Room room = new Room("one");

    @Before
    public void start() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        roomRepository.deleteAll();
        roomRepository.save(room);
    }

    @Test
    public void whenSaveRoom() throws Exception {
        mockMvc.perform(post("/room/")
                .contentType("application/json")
                .content("{ \"name\": \"two\" }"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("name").value("two"));
    }

    @Test
    public void whenUpdateRoom() throws Exception {
        mockMvc.perform(put("/room/")
                .contentType("application/json")
                .content("{ \"id\": " + room.getId() + ", \"name\": \"one\" }"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void whenGetAllRoom() throws Exception {
        mockMvc.perform(get("/room/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].name").value("one"));
    }

    @Test
    public void whenGetRoomById() throws Exception {
        mockMvc.perform(get("/room/{id}", room.getId()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("name").value("one"));
    }
}