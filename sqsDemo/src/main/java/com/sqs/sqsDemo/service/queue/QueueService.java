package com.sqs.sqsDemo.service.queue;

import com.sqs.sqsDemo.dto.PayloadDto;

public interface QueueService {

    void send(String queue, PayloadDto payload);

    Object read(String queue);

}
