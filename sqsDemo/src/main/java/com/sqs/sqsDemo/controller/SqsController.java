package com.sqs.sqsDemo.controller;

import com.sqs.sqsDemo.old.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queue")
public class SqsController {

    @Autowired
    private SqsService sqsService;

    @PostMapping("/send")
    public void send(@RequestBody String message) {
        System.out.println("Sending a transaction.");
        // Post message to the message queue named "OrderTransactionQueue"
        sqsService.send(message);
    }
}