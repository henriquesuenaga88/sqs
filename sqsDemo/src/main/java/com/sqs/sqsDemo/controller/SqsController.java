package com.sqs.sqsDemo.controller;

import com.amazonaws.util.StringUtils;
import com.sqs.sqsDemo.service.administrone.AdministroneSqsService;
import com.sqs.sqsDemo.service.bi.BiSqsService;
import com.sqs.sqsDemo.service.transformers.TransformersSqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SqsController {

    @Autowired
    private AdministroneSqsService administroneSqsService;

    @Autowired
    private TransformersSqsService transformersSqsService;

    @Autowired
    private BiSqsService biSqsService;

    @PostMapping("/send/{queueName}")
    public String send(@PathVariable String queueName, @RequestBody String message) {
        if (StringUtils.isNullOrEmpty(queueName)) return "";
        if (queueName.equals("administrone")) {
            System.out.println("Sending a transaction to administrone.");
            administroneSqsService.sendMessage(message);
        } else if (queueName.equals("transformers")) {
            System.out.println("Reading a transaction from transformers.");
            transformersSqsService.sendMessage(message);
        } else if (queueName.equals("bi")) {
            System.out.println("Reading a transaction from bi.");
            biSqsService.sendMessage(message);
        }

        return "okay!";
    }

    @GetMapping("/read/{queueName}")
    public String read(@PathVariable String queueName) {
        if (StringUtils.isNullOrEmpty(queueName)) return "";
        if (queueName.equals("administrone")) {
            System.out.println("Reading a transaction from administrone.");
            return administroneSqsService.readMessages();
        } else if (queueName.equals("transformers")) {
            System.out.println("Reading a transaction from transformers.");
            return transformersSqsService.readMessages();
        } else if (queueName.equals("bi")) {
            System.out.println("Reading a transaction from bi.");
            return biSqsService.readMessages();
        }

        return "404, queue not found!";

    }
}