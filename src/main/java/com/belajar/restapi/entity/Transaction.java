package com.belajar.restapi.entity;

import javax.persistence.*;

import lombok.Data;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * This is a Javadoc comment
 */
@Data /*Untuk Generate Getter*/
@Entity /*Untuk Generate Entity*/
@Transactional
public class Transaction {

    @Id /*Sebagai Id*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idUser")/*Sesuai dengan field yang ada pada database*/
    private Integer idUser;

    @Column(name = "idHardware")/*Sesuai dengan field yang ada pada database*/
    private Integer idHardware;

    @Column(name = "harga")/*Sesuai dengan field yang ada pada database*/
    private Long harga;

    @Column(name = "qty")/*Sesuai dengan field yang ada pada database*/
    private Long qty;

    @Column(name = "ppn")/*Sesuai dengan field yang ada pada database*/
    private Long ppn;

    @Column(name = "total")/*Sesuai dengan field yang ada pada database*/
    private Long total;

    @Column(name = "date")/*Sesuai dengan field yang ada pada database*/
    private Date date;
}
