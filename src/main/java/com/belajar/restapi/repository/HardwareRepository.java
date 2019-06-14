package com.belajar.restapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.belajar.restapi.entity.Hardware;

/**
 * This is a Javadoc comment
 * @param <T> the parameter of the class
 */
public interface HardwareRepository extends JpaRepository<Hardware, Integer> {
}