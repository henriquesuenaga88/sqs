package com.sqs.sqsDemo.old;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import org.springframework.stereotype.Service;

@Service
public class AwsEnvironmentVariableService implements SqsService {

    @Override
    public AWSCredentials getCredentials() {
        try {
            return new EnvironmentVariableCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the environment variable. " +
                            "Please make sure that your credentials variables are defined: " +
                            "AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY.",
                    e);
        }
    }


}
