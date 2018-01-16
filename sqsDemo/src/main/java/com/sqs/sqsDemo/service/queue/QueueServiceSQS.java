package com.sqs.sqsDemo.service.queue;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.sqs.sqsDemo.dto.PayloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("production")
public class QueueServiceSQS implements QueueService {

    @Autowired
    private AmazonSQS delegate;

    @Override
    public void send(String queueUrl, PayloadDto payload) {
        SendMessageRequest request = new SendMessageRequest(queueUrl, payload.toString());

        delegate.sendMessage(request);
    }

    @Override
    public List<Message> read(String queueUrl) {
        ReceiveMessageRequest received = new ReceiveMessageRequest(queueUrl);
        return delegate.receiveMessage(received).getMessages();
    }

}
