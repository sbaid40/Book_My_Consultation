package com.upgrad.notificationservice.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class SesEmailVerificationService {

    private SesClient sesClient;
    //private final FreeMarkerConfigurer configurer;
    private String fromEmail = "";//needs to be a verified email id
    private String accessKey;
    private String secretKey;

    @PostConstruct
    public void init(){
        accessKey="";
        secretKey="";
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider
                .create(AwsBasicCredentials.create(accessKey,secretKey));
        sesClient = SesClient.builder()
                .credentialsProvider(staticCredentialsProvider)
                .region(Region.US_EAST_1)
                .build();
    }
    //method to send email verification
    public void verifyEmail(String emailId){
        sesClient.verifyEmailAddress(req->req.emailAddress(emailId));
    }
}
