package com.example.boxchat_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)

public class BoxChatJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoxChatJavaApplication.class, args);
    }

}
