package com.belajar.restapi.controller;

import com.belajar.restapi.dto.TransactionDTO;


import com.belajar.restapi.service.TransactionService;

import com.belajar.restapi.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**ß
 * This is a Javadoc comment
 */
@RestController
@RequestMapping(value = "transactionresponse")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    private String service = "transactionresponse";


    @PostMapping /*Komunikasi API dengan jenis POST*/
    ResponseEntity<Response> create (@RequestBody @Validated TransactionDTO transactionDTO) /*Mengambil Request data dari Body dan melakukan Proses Validasi*/
    {

        Response response = new Response();

        response.setMessage("Berhasil Membuat Data");

        response.setData(transactionService.create(transactionDTO));

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
        response.setData(transactionService.findById(id));

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
        response.setData(transactionService.findAll());

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
        response.setData(transactionService.findById(id));

        transactionService.delete(id);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


}
