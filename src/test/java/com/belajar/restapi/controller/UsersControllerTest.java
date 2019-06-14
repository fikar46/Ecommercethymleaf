package com.belajar.restapi.controller;


import com.belajar.restapi.entity.Users;
import com.belajar.restapi.repository.UsersRepository;
import com.belajar.restapi.service.UsersService;
import com.belajar.restapi.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j


public class UsersControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }



    @Test
    public void create() throws Exception{
        String json =   "{\n" +
                "  \"id\": 8,\n" +
                "  \"username\": \"fikar46\",\n" +
                "  \"wallet\": 20000\n" +
                "}";
        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.username", Matchers.is("fikar46"))));

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Membuat Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }


    @Test
    public void update() throws Exception{
        int id = 2;
        String json =   "{\n" +
                "  \"id\": 2,\n" +
                "  \"username\": \"fikar\",\n" +
                "  \"wallet\": 20000\n" +
                "}";
        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.username", Matchers.is("fikar"))));

        mockMvc.perform(put("/users/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Update Data")))

                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));



    }

    @Test
    public void getByid() throws Exception {
        int id = 1;
        mockMvc.perform(get("/users/"+id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Mengambil Data Berdasarkan Id")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));

    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Menampilkan Seluruh Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }



    @Test
    public void deleteById() throws Exception{
        int id = 1;
        mockMvc.perform(delete("/users/"+id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Data Berhasil dihapus")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }
    @Test
    public void createfailed() throws Exception{
        String json =   "{\n" +
                "  \"username\": \"fikar46\",\n" +
                "  \"wallet\": 20000\n" +
                "}";
        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.username", Matchers.is("fikar46"))));

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
}