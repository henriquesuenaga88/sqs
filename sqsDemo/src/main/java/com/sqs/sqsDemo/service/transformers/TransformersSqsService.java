package com.sqs.sqsDemo.service.transformers;

import com.sqs.sqsDemo.service.queue.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransformersSqsService {

    public static final String QUEUE = "transformers";

    @Autowired
    private QueueService queueService;

    public void sendMessage(String message) {
        queueService.send(QUEUE, message);
    }

    public String readMessages() {
        return queueService.read(QUEUE);
    }

}