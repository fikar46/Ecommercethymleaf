package com.belajar.restapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is a Javadoc comment
 */
@Setter /*Untuk Generate Setter*/
@Getter /*Untuk Generate Getter*/
@Entity /*Untuk Generate Entity*/
@Transactional
public class Hardware {

    @Id /*Sebagai Id*/

    private Integer id;

    @Column(name = "namabarang")/*Sesuai dengan field yang ada pada database*/
    private String namabarang;

    @Column(name = "merk")/*Sesuai dengan field yang ada pada database*/
    private String merk;

    @Column(name = "harga")/*Sesuai dengan field yang ada pada database*/
    private Long harga;

    @Column(name = "stock")/*Sesuai dengan field yang ada pada database*/
    private Long stock;
}
