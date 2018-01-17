package com.sqs.sqsDemo.service.administrone;

import com.sqs.sqsDemo.service.queue.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministroneSqsService {

    public static final String QUEUE = "administrone";

    @Autowired
    private QueueService queueService;

    public void sendMessage(String message) {
        queueService.send(QUEUE, message);
    }

    public String readMessages() {
        return queueService.read(QUEUE);
    }

}