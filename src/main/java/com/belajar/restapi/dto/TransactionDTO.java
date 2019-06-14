package com.belajar.restapi.dto;

import com.belajar.restapi.entity.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * The type Account detail dto.
 *
 * @author mzulfikarmaylendra  on 10/08/18 11.31
 */
@Setter /*Untuk Generate Setter*/
@Getter /*Untuk Generate Getter*/
@NoArgsConstructor
public class TransactionDTO {
    private Integer id;
    private Integer idUser;
    private String username;
    private String namaHardware;
    @NotNull
    private Integer idHardware;
    private Long harga;
    @NotNull
    private Long qty;
    private Long ppn;
    private Long total;
    private Date date;
    /**
     * Instantiates a new Account detail dto.
     *
     * @param transaction             the hardware
     */
    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.idUser = transaction.getIdUser();
        this.idHardware = transaction.getIdHardware();
        this.harga = transaction.getHarga();
        this.qty=transaction.getQty();
        this.ppn= transaction.getPpn();
        this.total=transaction.getTotal();
        this.date=transaction.getDate();
    }
}
