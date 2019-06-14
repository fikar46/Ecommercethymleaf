package com.belajar.restapi.dto;


import com.belajar.restapi.entity.Hardware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * The type Account detail dto.
 *
 * @author mzulfikarmaylendra  on 10/08/18 11.31
 */
@Setter /*Untuk Generate Setter*/
@Getter /*Untuk Generate Getter*/
@NoArgsConstructor
public class HardwareDTO {
    @NotNull
    private Integer id;
    @NotNull
    private String namabarang;
    @NotNull
    private String merk;
    @NotNull
    private Long harga;
    @NotNull
    private Long stock;
    /**
     * Instantiates a new Account detail dto.
     *
     * @param hardware             the hardware
     */
    public HardwareDTO(Hardware hardware) {
        this.id = hardware.getId();
        this.namabarang = hardware.getNamabarang();
        this.merk = hardware.getMerk();
        this.harga = hardware.getHarga();
        this.stock = hardware.getStock();
    }
}
