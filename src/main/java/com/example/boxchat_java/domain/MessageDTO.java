package com.example.boxchat_java.domain;

import lombok.Data;

@Data
public class MessageDTO {
    private long sender;
    private long receiver;
    private String text;
    private String image;
}
