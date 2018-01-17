package com.sqs.sqsDemo.service.queue;

public interface QueueService {

    void send(String queue, String payload);

    String read(String queue);

}
