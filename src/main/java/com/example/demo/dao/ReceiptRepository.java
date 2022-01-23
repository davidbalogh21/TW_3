package com.example.demo.dao;

import com.example.demo.model.Receipt;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/***
 * In JpaRepository we find the default implementation for the database query provided by the Java Spring Framework.
 * The queries here will be automatically generated for our databases.
 * What is not a database (eg runtime, excel files, csv files, etc.) must be implemented.
 *
 * This is basically Repository Pattern
 */
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> getAllByUser(User user);
}
