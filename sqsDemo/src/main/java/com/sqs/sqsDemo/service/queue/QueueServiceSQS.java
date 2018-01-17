package com.sqs.sqsDemo.service.queue;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("production")
public class QueueServiceSQS implements QueueService {

    @Autowired
    private AmazonSQS delegate;

    @Override
    public void send(String queueUrl, String payload) {
        SendMessageRequest request = new SendMessageRequest(queueUrl, payload);

        delegate.sendMessage(request);
    }

    @Override
    public String read(String queueUrl) {
        ReceiveMessageRequest received = new ReceiveMessageRequest(queueUrl);
        final StringBuilder messageBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            buildMessages(received, messageBuilder);
        }

        return messageBuilder.toString();
    }

    private void buildMessages(ReceiveMessageRequest received, StringBuilder messageBuilder) {
        delegate
                .receiveMessage(received)
                .getMessages()
                .forEach(m -> messageBuilder.append(m.getBody()).append(" "));
    }

}
