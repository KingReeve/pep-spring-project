package com.example.service;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageService {

    public MessageRepository messageRepository;
    public AccountRepository accountRepository;
    public AccountService accountService;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountService accountService){
        this.messageRepository = messageRepository;
        // this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    public List<Message> findAllMessages(){
        return messageRepository.findAll();
    }

    public Message postMessage(Message message){
        if(message.getMessage_text().isEmpty()
        || message.getMessage_text().length() > 255
        || accountService.findUser(message.getPosted_by()) == null
        ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Message");
        }else{
            return messageRepository.save(message);
        }
    }

    public Message findMessageById(int Id) {
        Optional<Message> messageOptional = messageRepository.findById(Id);
        if(messageOptional.isPresent()){
            Message message = messageOptional.get();
            return message;
        }
        return null;
    }

    public Integer deleteMessageByMessageId(int Id){
        Optional<Message> messageOptional = messageRepository.findById(Id);
        if(messageOptional.isPresent()){
            messageRepository.deleteById(Id);
            return 1;
        }
        return null;
    }

    public List<Message> getAllMessagesByPostedBy(int id){
        return messageRepository.findAllMessagesByPostedBy(id);        
    }
    
    public Integer updateMessageById(int id, Message message){
        Message oldMessage = findMessageById(id);
        if(oldMessage == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Message");            
        }

        if(message.getMessage_text().isEmpty()
        || message.getMessage_text().length() > 255
        ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Message");
        }else{
            messageRepository.updateMessageById(id, message.getMessage_text());
            return 1;
        }
    }

}
