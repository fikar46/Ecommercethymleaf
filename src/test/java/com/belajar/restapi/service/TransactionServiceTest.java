package com.belajar.restapi.service;

import com.belajar.restapi.dto.TransactionDTO;
import com.belajar.restapi.dto.UsersDTO;
import com.belajar.restapi.entity.Hardware;
import com.belajar.restapi.entity.Transaction;
import com.belajar.restapi.entity.Users;
import com.belajar.restapi.error.Datanotfound;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class TransactionServiceTest {
    @Autowired
    TransactionService transactionService;

    @Autowired
    UsersService usersService;
    @Autowired
    HardwareService hardwareService;

    @Test
    public void findAll() {
        List<Transaction> transactions= (List<Transaction>) transactionService.findAll();
        log.info("size : "+transactions.size());
        assertThat(transactions.size())
                .isNotNull();
    }

    @Test
    public void findById() {
        int id =1;
        Transaction transaction =  transactionService.findById(id);
        assertThat(transaction.getId())
                .isEqualTo(id);
    }

    @Test
    public void create() {
        TransactionDTO transactionDTO = new TransactionDTO();
        List<Transaction> listtransaction= (List<Transaction>) transactionService.findAll();
        int id = listtransaction.size()+1;
        int idHardware = 3;
        int idUser = 2;
        long qty = 1;
        Date date=  new Date();


        Hardware dataharga = hardwareService.findById(idHardware);
//        Transaction transaction = new Transaction();
        long harga = dataharga.getHarga();
        transactionDTO.setHarga(harga);
        long totalharga = harga * qty;
        long ppn = totalharga*10/100;
        transactionDTO.setPpn(ppn);
        long total = totalharga + ppn;
        transactionDTO.setTotal(total);

        transactionDTO.setId(id);
        transactionDTO.setIdUser(idUser);
        transactionDTO.setIdHardware(idHardware);
        transactionDTO.setQty(qty);
        transactionDTO.setDate(date);
        /*Kalkulasi dari transaksi ke wallet user*/
        Users users = usersService.findById(transactionDTO.getIdUser());
        if(users.getWallet() < total){
            throw  new Datanotfound("Walletmu tidak mencukupi");
        }
        long userwallet = users.getWallet()-total;
        long stockhardware = dataharga.getStock()-qty;
        dataharga.setStock(stockhardware);
        users.setWallet(userwallet);
        Users update = usersService.findById(transactionDTO.getIdUser());
        TransactionDTO create = transactionService.create(transactionDTO);

        assertThat(create.getId())
                .isEqualTo(id);
        assertThat(create.getIdHardware())
                .isEqualTo(idHardware);
        assertThat(create.getIdUser())
                .isEqualTo(idUser);
        assertThat(create.getQty())
                .isEqualTo(qty);
        assertThat(create.getDate())
                .isEqualTo(date);
        assertThat(update.getWallet())
                .isGreaterThan(users.getId());
    }

    @Test
    public void delete() {
        int id = 2;
        log.info("--- sebelum terdelete ---");
        List<Transaction> listtransaction= (List<Transaction>) transactionService.findAll();
        log.info("size "+listtransaction.size());
        transactionService.delete(id);
        log.info(id+" terdelete");
        log.info("--- sesudah terdelete ---");
        List<Transaction> newTransaction= (List<Transaction>) transactionService.findAll();
        log.info("size "+newTransaction.size());
        assertThat(listtransaction.size())
                .isGreaterThan(newTransaction.size());
    }
}