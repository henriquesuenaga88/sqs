# SQS

This project is a study about SQS using with AmazonSQS.
It uses:
 - AmazonSQS
 - Spring Boot
 - Maven
 - IAM
 - AWS

- To run this project you must have an IAM Role and set the credentials on the default folder (~.aws/credentials) and create a queue on Amazon SQS. 

On this example I used three queues: 
  - administrone
  - transformers
  - bi
  
It's possible to send/read message from those queues using the following:

 - /send/{queueName}
 - /read/{queueName}
 
 The send api requires a body with a string message.
