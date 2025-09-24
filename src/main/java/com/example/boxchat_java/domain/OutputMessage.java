package com.example.boxchat_java.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OutputMessage {
    private Long id;
    private String text;
    private String image;
    private LocalDateTime createdAt;

    private Long senderId;
    private String senderAvatar;

    private Long receiverId;
    private String receiverAvatar;

    public OutputMessage(Message message) {
        this.id = message.getId();
        this.text = message.getText();
        this.image = message.getImage();
        this.createdAt = message.getCreatedAt();

        this.senderId = message.getSender().getId();
        this.senderAvatar = message.getSender().getAvatar();

        this.receiverId = message.getReceiver().getId();
        this.receiverAvatar = message.getReceiver().getAvatar();
    }
}

