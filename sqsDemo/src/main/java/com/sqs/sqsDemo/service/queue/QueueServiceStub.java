package com.sqs.sqsDemo.service.queue;

import com.sqs.sqsDemo.dto.PayloadDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("local")
public class QueueServiceStub implements QueueService {

    List<PayloadDto> payloads = new ArrayList<>();

    @Override
    public void send(String queue, PayloadDto payload) {
        payloads.add(payload);
    }

    @Override
    public Object read(String queue) {
        return payloads;
    }

}
