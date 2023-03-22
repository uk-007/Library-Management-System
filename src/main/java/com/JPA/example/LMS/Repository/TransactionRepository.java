package com.JPA.example.LMS.Repository;

import com.JPA.example.LMS.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query(value = "select * from transaction t where t.card_card_no=:cardId AND t.transaction_status='SUCCESS'",
            nativeQuery = true)
    //in semicolon variable
    //nativequery = true telling springboot that this is regular sql query not any jpa defined query
    List<Transaction> getAllSuccessTxnWithCardNo(int cardId);
}
