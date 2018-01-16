package com.sqs.sqsDemo.old;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import java.util.List;
import java.util.Map;

public interface SqsService {

    public abstract AWSCredentials getCredentials();

    default AmazonSQS getSqs() {
        System.out.println("===========================================");
        System.out.println("Getting Started with Amazon SQS");
        System.out.println("===========================================\n");

        return AmazonSQSClientBuilder.standard()
            .withRegion(Regions.US_EAST_2)
            .build();
    }

    default String createQueue(String queueName) {
        System.out.println("Creating a new SQS queue called " + queueName + ".\n");
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        return getSqs().createQueue(createQueueRequest).getQueueUrl();
    }

    default void listQueue() {
        System.out.println("Listing all queues in your account.\n");
        System.out.println("===========================================");
        for (String queueUrl : getSqs().listQueues().getQueueUrls()) {
            System.out.println("  QueueUrl: " + queueUrl);
        }

        System.out.println("===========================================");
    }

    default void send(String url, String message) {
        System.out.println("Sending a message to my queue.\n");
        getSqs().sendMessage(new SendMessageRequest(url, message));
    }

    default void receiveMessage(String url) {
        // Receive messages
        System.out.println("Receiving messages from MyQueue.\n");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(url);
        List<Message> messages = getSqs().receiveMessage(receiveMessageRequest).getMessages();
        for (Message message : messages) {
            System.out.println("  Message");
            System.out.println("    MessageId:     " + message.getMessageId());
            System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("    Body:          " + message.getBody());
            for (Map.Entry<String, String> entry : message.getAttributes().entrySet()) {
                System.out.println("  Attribute");
                System.out.println("    Name:  " + entry.getKey());
                System.out.println("    Value: " + entry.getValue());
            }
        }
        System.out.println();
    }

    default void deleteMessage(String url) {
        System.out.println("Deleting a message.\n");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(url);
        List<Message> messages = getSqs().receiveMessage(receiveMessageRequest).getMessages();
        String messageReceiptHandle = messages.get(0).getReceiptHandle();
        getSqs().deleteMessage(new DeleteMessageRequest(url, messageReceiptHandle));
    }

    default void deleteQueue(String url) {
        // Delete a queue
        System.out.println("Deleting the test queue.\n");
        getSqs().deleteQueue(new DeleteQueueRequest(url));
    }

}
