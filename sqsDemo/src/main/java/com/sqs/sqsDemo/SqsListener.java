package com.sqs.sqsDemo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsListener {

    @JmsListener(destination = "SuenagaQueue", containerFactory = "sqsFactory")
    public void receiveMessage(String transaction) {
        System.out.println("Received <" + transaction + ">");
    }
}