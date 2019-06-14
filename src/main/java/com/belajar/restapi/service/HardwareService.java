package com.belajar.restapi.service;

import java.util.List;
import java.util.Optional;

import com.belajar.restapi.dto.HardwareDTO;
import com.belajar.restapi.entity.Hardware;
import com.belajar.restapi.error.Datanotfound;
import com.belajar.restapi.repository.HardwareRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is a Javadoc comment
 */
@Service
@Slf4j
public class HardwareService {
    @Autowired
    private HardwareRepository hardwareRepository;
    /**
     * This is a Javadoc comment
     * @return List<Hardware> get all data
     * get all data
     */
    public List<Hardware> findAll() {
        return hardwareRepository.findAll();
    }
    /**
     * This is a get data by id
     * @param id the id to be used
     * @return Hardware get data by id
     */
    public Hardware findById(Integer id) {
        Optional<Hardware> optionalHardware = hardwareRepository.findById(id);
        if(!optionalHardware.isPresent()){
            throw  new Datanotfound("produk tidak ditemukan");
        }
        return optionalHardware.get();
    }
    /**
     * This is a Javadoc comment
     * @param id the id to be used
     * @param hardwareDTO the hardware to be used
     * @return Hardware update
     * update data by id
     */
    public HardwareDTO update(Integer id, HardwareDTO hardwareDTO) {
        Hardware hardware = new Hardware();
        hardware.setId(id);
        hardware.setNamabarang(hardwareDTO.getNamabarang());
        hardware.setHarga(hardwareDTO.getHarga());
        hardware.setMerk(hardwareDTO.getMerk());
        hardware.setStock(hardwareDTO.getStock());
        hardwareRepository.save(hardware);
        return hardwareDTO;
    }
    /**
     * This is a Javadoc comment
     * @param hardwareDTO the hardware to be used
     * @return Hardware create data
     * create data
     */
    public HardwareDTO create(HardwareDTO hardwareDTO) {
        Hardware hardware = new Hardware();
        hardware.setId(hardwareDTO.getId());
        hardware.setNamabarang(hardwareDTO.getNamabarang());
        hardware.setHarga(hardwareDTO.getHarga());
        hardware.setMerk(hardwareDTO.getMerk());
        hardware.setStock(hardwareDTO.getStock());
        hardwareRepository.save(hardware);
        return hardwareDTO;
    }
    /**
     * This is a Javadoc comment
     * @param id the id to be used
     * delete data
     */
    public void delete(Integer id) {
        hardwareRepository.deleteById(id);
    }
}
