package com.belajar.restapi.entity;

import javax.persistence.*;

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
public class Users {

    @Id /*Sebagai Id*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")/*Sesuai dengan field yang ada pada database*/
    private String username;


    @Column(name = "wallet")/*Sesuai dengan field yang ada pada database*/
    private Long wallet;

    @Column(name = "password")/*Sesuai dengan field yang ada pada database*/
    private String password;
}
