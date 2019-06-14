package com.belajar.restapi.controller;

import com.belajar.restapi.service.HardwareService;
import com.belajar.restapi.service.TransactionService;
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
public class TransactionControllerTest {
    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }
    @Test
    public void create() throws Exception{
        String json =   "{\n" +
                "  \"date\": \"2019-05-29T05:19:48.269Z\",\n" +
                "  \"id\": 2,\n" +
                "  \"idHardware\": 1,\n" +
                "  \"idUser\": 2,\n" +
                "  \"qty\": 8\n" +
                "}";
        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.idHardware", Matchers.is(1))));

        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.service", Matchers.is("com.belajar.restapi.controller.TransactionControllercreate")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Membuat Data")))

                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void getByid() throws Exception{
        int id = 1;
        mockMvc.perform(get("/transaction/"+id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.service", Matchers.is("com.belajar.restapi.controller.TransactionControllergetByid")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Mengambil Data Berdasarkan Id")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void findAll() throws Exception{
        mockMvc.perform(get("/transaction")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.service", Matchers.is("com.belajar.restapi.controller.TransactionControllerfindAll")))
                .andExpect(jsonPath("$.message", Matchers.is("Berhasil Menampilkan Seluruh Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void deleteById() throws Exception{
        int id = 3;
        mockMvc.perform(delete("/transaction/"+id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.service", Matchers.is("com.belajar.restapi.controller.TransactionControllerdeleteById")))
                .andExpect(jsonPath("$.message", Matchers.is("Data Berhasil dihapus")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }
}