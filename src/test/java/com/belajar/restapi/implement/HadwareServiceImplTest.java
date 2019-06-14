package com.belajar.restapi.implement;

import com.belajar.restapi.dto.HardwareDTO;
import com.belajar.restapi.entity.Hardware;
import com.belajar.restapi.entity.Users;
import com.belajar.restapi.error.Datanotfound;
import com.belajar.restapi.service.HardwareService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class HadwareServiceImplTest {
    @Autowired
    HardwareService hardwareService;
    @Test
    public void findAll() {
        List<Hardware> hardware= (List<Hardware>) hardwareService.findAll();
        log.info("size : "+hardware.size());
        assertThat(hardware.size())
                .isNotNull();
    }

    @Test
    public void findById() {
        int id =3;
        Hardware hardwareServiceById =  hardwareService.findById(id);
        if(hardwareServiceById==null){
            throw  new Datanotfound("Hardware tidak ditemukan");
        }
        log.info("hasil : ");
        log.info("nama barang : " +hardwareServiceById.getNamabarang());
        log.info("harga : " + hardwareServiceById.getHarga());
        assertThat(hardwareServiceById.getId())
                .isEqualTo(id);
    }

    @Test
    public void update() {
        HardwareDTO hardwareDTO = new HardwareDTO();
        int id = 1;
        long harga = 340000;
        hardwareDTO.setHarga(harga);
        HardwareDTO update= hardwareService.update(id,hardwareDTO);
        assertThat(update.getHarga())
                .isEqualTo(harga);
    }

    @Test
    public void create() {
        HardwareDTO hardwareDTO = new HardwareDTO();
        List<Hardware> listhardware= (List<Hardware>) hardwareService.findAll();
        int id = listhardware.size()+1;
        String namabarang = "lg monitor";
        String merk = "LG";
        long harga = 1500000;
        long stock = 10;

        log.info("--- set id ----");
        hardwareDTO.setId(id);
        log.info("--- set nama barang ----");
        hardwareDTO.setNamabarang(namabarang);
        log.info("--- set merk ----");
        hardwareDTO.setMerk(merk);
        log.info("--- set harga ---");
        hardwareDTO.setHarga(harga);
        log.info("--- set stock ---");
        hardwareDTO.setStock(stock);
        HardwareDTO create = hardwareService.create(hardwareDTO);
        log.info("--- get id ---");
        log.info("id: "+create.getId());
        log.info("--- get nama barang ---");
        log.info("id: "+create.getNamabarang());
        log.info("--- get merk ---");
        log.info("id: "+create.getMerk());
        log.info("--- get stock ---");
        log.info("id: "+create.getStock());
        log.info("--- get harga ---");
        log.info("id: "+create.getHarga());
        assertThat(create.getId())
                .isEqualTo(id);
        assertThat(create.getHarga())
                .isEqualTo(harga);
        assertThat(create.getMerk())
                .isEqualTo(merk);
        assertThat(create.getNamabarang())
                .isEqualTo(namabarang);
        assertThat(create.getStock())
                .isEqualTo(stock);
    }

    @Test
    public void delete() {
        int id = 1;
        log.info("--- sebelum terdelete ---");
        List<Hardware> listhardware= (List<Hardware>) hardwareService.findAll();
        log.info("size "+listhardware.size());
        hardwareService.delete(id);
        log.info(id+" terdelete");
        log.info("--- sesudah terdelete ---");
        List<Hardware> newHardware= (List<Hardware>) hardwareService.findAll();
        log.info("size "+newHardware.size());
        assertThat(listhardware.size())
                .isGreaterThan(newHardware.size());
    }
}