package com.sqs.sqsDemo.service.bi;

import com.sqs.sqsDemo.service.queue.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiSqsService {

    @Autowired
    private QueueService queue;

    public void doSomething() {

        queue.send("blabla", "Conteudo");

        queue.read("blabla");
    }

}