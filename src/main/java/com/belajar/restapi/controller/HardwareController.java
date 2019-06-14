package com.belajar.restapi.controller;

import com.belajar.restapi.dto.HardwareDTO;
import com.belajar.restapi.service.HardwareService;
import com.belajar.restapi.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

/**
 * This is a Javadoc comment
 */
@RestController
@RequestMapping(value = "hardware")
public class HardwareController {
    private static final Log LOG = LogFactory.getLog(HardwareController.class);
    @Autowired
    HardwareService hardwareService;

    private String service = "Hardware";



    @PostMapping /*Komunikasi API dengan jenis POST*/
    ResponseEntity<Response> create (@RequestBody @Validated HardwareDTO hardwareDTO) /*Mengambil Request data dari Body dan melakukan Proses Validasi*/
    {

        /*Memanggil Class Response yang telah dibuat*/
        Response response = new Response();

        response.setMessage("Berhasil Membuat Data");

        /*Set Data Dari Database*/
        response.setData(hardwareService.create(hardwareDTO));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping(value = "/{id}")/*Komunikasi API dengan jenis PUT*/
    ResponseEntity<Response> update (@PathVariable ("id")Integer id, @RequestBody @Validated HardwareDTO hardwareDTO) /*Mengambil Request data dari Body dan melakukan Proses Validasi, diseleksi berdasarkan id*/
    {

        Response response = new Response();
        response.setMessage("Berhasil Update Data");

        /*Set Data Dari Database*/
        response.setData(hardwareService.update(id, hardwareDTO));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping(value = "/{id}")/*Komunikasi API dengan jenis GET*/
    ResponseEntity<Response> getByid (@PathVariable ("id")Integer id)/*Mengambil Request data dari Berdasarkan id*/
    {
        /*Informasi Tentang Nama Method*/

        /*Memanggil Class Response yang telah dibuat*/
        Response response = new Response();

        response.setMessage("Berhasil Mengambil Data Berdasarkan Id");
        LOG.info(id);
        /*Set Data Dari Database*/
        response.setData(hardwareService.findById(id));

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
        response.setData(hardwareService.findAll());

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
        response.setData(hardwareService.findById(id));

        hardwareService.delete(id);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


}
