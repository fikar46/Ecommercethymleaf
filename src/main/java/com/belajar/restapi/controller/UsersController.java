package com.belajar.restapi.controller;

import com.belajar.restapi.dto.HardwareDTO;
import com.belajar.restapi.dto.UsersDTO;
import com.belajar.restapi.service.UsersService;
import com.belajar.restapi.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * This is a Javadoc comment
 */
@Slf4j
@RestController
@RequestMapping(value = "users")
public class UsersController {

    @Autowired
    UsersService usersService;

    private String service = "Users";


    @PostMapping /*Komunikasi API dengan jenis POST*/
    ResponseEntity<Response> create (@RequestBody @Validated UsersDTO usersDTO) /*Mengambil Request data dari Body dan melakukan Proses Validasi*/
    {

        /*Memanggil Class Response yang telah dibuat*/
        Response response = new Response();

        response.setMessage("Berhasil Membuat Data");

        /*Set Data Dari Database*/
        response.setData(usersService.create(usersDTO));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping(value = "/{id}")/*Komunikasi API dengan jenis PUT*/
    ResponseEntity<Response> update (@PathVariable ("id")Integer id, @RequestBody @Validated UsersDTO usersDTO) /*Mengambil Request data dari Body dan melakukan Proses Validasi, diseleksi berdasarkan id*/
    {


        Response response = new Response();

        response.setMessage("Berhasil Update Data");

        /*Set Data Dari Database*/
        response.setData(usersService.update(id, usersDTO));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping(value = "/{id}")/*Komunikasi API dengan jenis GET*/
    ResponseEntity<Response> getByid (@PathVariable ("id")Integer id)/*Mengambil Request data dari Berdasarkan id*/
    {

        /*Memanggil Class Response yang telah dibuat*/
        Response response = new Response();
        response.setMessage("Berhasil Mengambil Data Berdasarkan Id");
        /*Set Data Dari Database*/
        response.setData(usersService.findById(id));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping/*Komunikasi API dengan jenis GET*/
    ResponseEntity<Response> findAll ()
    {
        /*Memanggil Class Response yang telah dibuat*/
        Response response = new Response();
        response.setMessage("Berhasil Menampilkan Seluruh Data");

        /*Set Data Dari Database*/
        response.setData(usersService.findAll());

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @DeleteMapping(value = "/{id}")/*Komunikasi API dengan jenis GET*/
    ResponseEntity<Response> deleteById (@PathVariable ("id")Integer id)/*Mengambil Request data dari Berdasarkan id*/
    {


        /*Memanggil Class Response yang telah dibuat*/
        Response response = new Response();
        response.setMessage("Data Berhasil dihapus");
        response.setData(usersService.findById(id));

        usersService.delete(id);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


}
