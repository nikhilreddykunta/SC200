package com.example.UserProfile.kafka.producer;


import com.example.UserProfile.domain.Challenge;
import com.example.UserProfile.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, UserProfile> kafkaTemplate;

    private static final String TOPIC = "test5";
    private static final String TOPIC2 = "test-challenge";




    public void putIntoTopic(UserProfile user){
        System.out.println("inside putIntotOPIC "+user);
        kafkaTemplate.send(TOPIC,user);
        System.out.println("we have executted send topic");
    }

}