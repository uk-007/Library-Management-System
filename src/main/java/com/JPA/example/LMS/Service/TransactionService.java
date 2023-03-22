package com.JPA.example.LMS.Service;

import com.JPA.example.LMS.DTO.IssueBookRequestDto;
import com.JPA.example.LMS.DTO.IssueBookResponseDto;
import com.JPA.example.LMS.Entity.Book;
import com.JPA.example.LMS.Entity.LibraryCard;
import com.JPA.example.LMS.Entity.Transaction;
import com.JPA.example.LMS.Enum.CardStatus;
import com.JPA.example.LMS.Enum.TransactionStatus;
import com.JPA.example.LMS.Repository.BookRepository;
import com.JPA.example.LMS.Repository.LibraryCardRepository;
import com.JPA.example.LMS.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    LibraryCardRepository libraryCardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private JavaMailSender emailSender;

    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {
        //create transaction object
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        LibraryCard card;
        try{
            card = libraryCardRepository.findById(issueBookRequestDto.getCardId()).get();
        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid Card");
            transactionRepository.save(transaction);
            throw new Exception("invalid Card");   //if exception is thrown flow of code goes to controller class
        }

        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("invalid Book id");
            transactionRepository.save(transaction);
            throw new Exception("invalid book id");

        }

        //both card and book are valid
        transaction.setBook(book);
        transaction.setCard(card);

        if(card.getStatus()!= CardStatus.ACTIVATED){                   //if card is de-activated
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("your card is not activated");
            transactionRepository.save(transaction);
            throw new Exception("your card is not activated");
        }
        if(book.isIssued()==true){                                   //if book is already issued
            transaction.setMessage("Sorry!book is already issued");
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Sorry!book is already issued");
        }

        //all condtions checked, book can be issued

        book.setIssued(true);
        transaction.setMessage("Transaction is successful");
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        book.getTransaction().add(transaction);
        book.setCard(card);
        card.getTransaction().add(transaction);
        card.getBooksIssued().add(book);

        libraryCardRepository.save(card); //will save book and transaction also

        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setBookName(book.getTitle());
        issueBookResponseDto.setTransactionId(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);


        String text = "Congratulations!!!. "+card.getStudent().getName() + " Book issued -"+book.getTitle()+ "\n" + " Please return in 15 days.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dummyservice07@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Issue Book Notification");
        message.setText(text);
        emailSender.send(message);



        return issueBookResponseDto;

    }

    public String getTransaction(int cardId){
        List<Transaction> transactionList = transactionRepository.getAllSuccessTxnWithCardNo(cardId);
        String ans= "";
        for(Transaction t : transactionList){
            ans += t.getTransactionNumber();
            ans += "\n";
        }
        return ans;
    }
}
