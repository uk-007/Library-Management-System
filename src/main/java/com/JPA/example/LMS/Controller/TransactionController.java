package com.JPA.example.LMS.Controller;

import com.JPA.example.LMS.DTO.IssueBookRequestDto;
import com.JPA.example.LMS.DTO.IssueBookResponseDto;
import com.JPA.example.LMS.Entity.Transaction;
import com.JPA.example.LMS.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) throws Exception{
        IssueBookResponseDto issueBookResponseDto;
        try{
            issueBookResponseDto = transactionService.issueBook(issueBookRequestDto);


        }catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(issueBookResponseDto,HttpStatus.ACCEPTED);
    }

    //to make api to get list of transaction for particular card
    @GetMapping("/get")
    public String getTransaction(@RequestParam("cardId") int cardId){    //making api with custom query

       return transactionService.getTransaction(cardId);
    }

}
