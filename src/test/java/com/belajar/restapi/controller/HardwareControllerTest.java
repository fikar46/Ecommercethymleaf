package com.belajar.restapi.controller;

import com.belajar.restapi.service.HardwareService;
import com.belajar.restapi.service.UsersService;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class HardwareControllerTest {
    private MockMvc mockMvc;

    @Mock
    private HardwareService hardwareService;

    @InjectMocks
    private HardwareController hardwareController;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(hardwareController).build();
    }
    @Test
    public void create() throws Exception{
        String json =   "{\n" +
                "  \"harga\": 20000,\n" +
                "  \"id\": 8,\n" +
                "  \"merk\": \"honda\",\n" +
                "  \"namabarang\": \"spakboard\",\n" +
                "  \"stock\": 8\n" +
                "}";
        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.merk", Matchers.is("honda"))));

        mockMvc.perform(post("/hardware")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.service", Matchers.is("com.belajar.restapi.controller.HardwareControllercreate")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Membuat Data")))

                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void update() throws Exception{
        int id = 2;
        String json =   "{\n" +
                "  \"harga\": 20000,\n" +
                "  \"id\": 2,\n" +
                "  \"merk\": \"honda\",\n" +
                "  \"namabarang\": \"spakboard\",\n" +
                "  \"stock\": 8\n" +
                "}";
        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.merk", Matchers.is("honda"))));

        mockMvc.perform(put("/hardware/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.service", Matchers.is("com.belajar.restapi.controller.HardwareControllerupdate")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Update Data")))

                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void getByid() throws Exception{
        int id = 1;
        mockMvc.perform(get("/hardware/"+id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.service", Matchers.is("com.belajar.restapi.controller.HardwareControllergetByid")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Mengambil Data Berdasarkan Id")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void findAll() throws Exception{
        mockMvc.perform(get("/hardware")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.service", Matchers.is("com.belajar.restapi.controller.HardwareControllerfindAll")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Menampilkan Seluruh Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void deleteById() throws Exception{
        int id = 3;
        mockMvc.perform(delete("/hardware/"+id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.service", Matchers.is("com.belajar.restapi.controller.HardwareControllerdeleteById")))
                .andExpect(jsonPath("$.message", Matchers.is("Data Berhasil dihapus")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }
}