package com.sqs.sqsDemo.service.queue;

import com.amazonaws.services.sqs.model.Message;
import com.sqs.sqsDemo.dto.PayloadDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
@Profile("local")
public class QueueServiceStub implements QueueService {

    String payload;

    @Override
    public void send(String queue, String payload) {
        this.payload = payload;
    }

    @Override
    public String read(String queue) {
        return payload;
    }

}
