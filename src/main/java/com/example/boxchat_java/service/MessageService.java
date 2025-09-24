package com.example.boxchat_java.service;

import com.example.boxchat_java.domain.Message;
import com.example.boxchat_java.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message message) {
        return this.messageRepository.save(message);
    }

    public List<Message> findOneChat(long senderId1, long receiverId1, long senderId2, long receiverId2) {
        return this.messageRepository.findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByCreatedAtAsc(senderId1, receiverId1, senderId2, receiverId2);
    }
}
