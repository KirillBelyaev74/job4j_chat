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
import ru.job4j.job4j_chat.model.Person;
import ru.job4j.job4j_chat.model.Role;
import ru.job4j.job4j_chat.repository.PersonRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class PersonControllerTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final Person person = new Person("kirill", "kirill");

    @Before
    public void start() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        personRepository.findAll();
        person.setRole(new Role("user"));
        personRepository.save(person);
    }

    @Test
    public void whenSavePerson() throws Exception {
        this.mockMvc.perform(post("/person/")
                .contentType("application/json")
                .content("{ \"login\": \"nelli\", \"password\": \"nelli\", \"role\": { \"role\" : \"admin\" } }"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("login").value("nelli"));
    }

    @Test
    public void whenFindAllPerson() throws Exception {
        this.mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].login").value("kirill"));
    }

    @Test
    public void whenFindPersonById() throws Exception {
        this.mockMvc.perform(get("/person/{id}", person.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("login").value("kirill"));
    }

    @Test
    public void whenUpdatePersonById() throws Exception {
        this.mockMvc.perform(put("/person/")
                .contentType("application/json")
                .content("{ \"id\": \"" + person.getId() +
                        "\", \"login\": \"petr\", \"password\": \"petr\", \"role\": { \"role\" : \"admin\" } }"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeletePersonById() throws Exception {
        this.mockMvc.perform(delete("/person/{id}", person.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}