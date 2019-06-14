package com.belajar.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belajar.restapi.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is a Javadoc comment
 * @param <T> the parameter of the class
 */
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    @Query(value = "select * from TRANSACTION where ID_USER=:id",nativeQuery = true)
    Transaction findByIdUser(@Param("id")int id);
}
