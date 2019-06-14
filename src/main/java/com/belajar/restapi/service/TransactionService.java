package com.belajar.restapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.belajar.restapi.dto.TransactionDTO;
import com.belajar.restapi.entity.Hardware;
import com.belajar.restapi.entity.Transaction;
import com.belajar.restapi.entity.Users;
import com.belajar.restapi.error.Datanotfound;
import com.belajar.restapi.repository.TransactionRepository;
import com.belajar.restapi.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is a Javadoc comment
 */
@Service
@Slf4j
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    HardwareService hardwareService;

    @Autowired
    UsersService usersService;
    /**
     * This is a Javadoc comment
     * @return List<Transaction> get all data
     * get all data
     */
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
    /**
     * This is a get data by id
     * @param id the id to be used
     * @return Transaction get data by id
     */
    public Transaction findById(Integer id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if(!optionalTransaction.isPresent()){
            throw  new Datanotfound("Transaksi tidak ditemukan");
        }
        return optionalTransaction.get();
    }
    public TransactionDTO findByUsername(String username) {
        Users users = usersService.findByUsername(username);
        Transaction transaction = transactionRepository.findByIdUser(users.getId());
        Hardware hardware = hardwareService.findById(transaction.getIdHardware());
        TransactionDTO datatransaksi = new TransactionDTO();
        datatransaksi.setNamaHardware(hardware.getNamabarang());
        datatransaksi.setQty(transaction.getQty());
        datatransaksi.setTotal(transaction.getTotal());
        log.info(transaction.getHarga()+ "masuk");
        return datatransaksi;
    }
    /**
     * This is a Javadoc comment
     * @param transactionDTO the hardware to be used
     * @return Transaction create data
     * create data
     */
    public TransactionDTO create(TransactionDTO transactionDTO) {
        Hardware dataharga = hardwareService.findById(transactionDTO.getIdHardware());
        Users usersData = usersService.findByUsername(transactionDTO.getUsername());
        Transaction transaction = new Transaction();
        long harga = dataharga.getHarga();
        transaction.setHarga(harga);
        long totalharga = harga * transactionDTO.getQty();
        long ppn = totalharga*10/100;
        transaction.setPpn(ppn);
        long total = totalharga + ppn;
        transactionDTO.setPpn(ppn);
        transactionDTO.setHarga(harga);
        transactionDTO.setTotal(total);
        transactionDTO.setIdUser(usersData.getId());
        transaction.setTotal(total);
        transaction.setId(transactionDTO.getId());
        transaction.setIdUser(usersData.getId());
        transaction.setIdHardware(transactionDTO.getIdHardware());
        transaction.setQty(transactionDTO.getQty());
        Date date = new Date();
        transaction.setDate(date);
        transactionDTO.setDate(date);
        transactionDTO.setId(1);
        /*Kalkulasi dari transaksi ke wallet user*/
        Users users = usersService.findById(transactionDTO.getIdUser());
        if(users.getWallet() < total){
            throw  new Datanotfound("Walletmu tidak mencukupi");
        }
        long userwallet = users.getWallet()-total;
        long stockhardware = dataharga.getStock()-transactionDTO.getQty();
        dataharga.setStock(stockhardware);
        users.setWallet(userwallet);
        transactionRepository.save(transaction);
        return transactionDTO;

    }
    /**
     * This is a Javadoc comment
     * @param id the id to be used
     * delete data
     */
    public void delete(Integer id) {
        transactionRepository.deleteById(id);
    }
}
