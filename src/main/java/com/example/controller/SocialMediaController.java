package com.example.controller;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import com.example.service.MessageService;
import com.example.service.AccountService;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    MessageRepository messageRepository;
    MessageService messageService;
    AccountService accountService;


    
    @GetMapping(path = "/messages")
    public List<Message> getAllMessages(){
        return messageService.findAllMessages();
    }

}
