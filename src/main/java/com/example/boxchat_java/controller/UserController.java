package com.example.boxchat_java.controller;

import com.example.boxchat_java.domain.Message;
import com.example.boxchat_java.domain.MessageDTO;
import com.example.boxchat_java.domain.OutputMessage;
import com.example.boxchat_java.domain.User;
import com.example.boxchat_java.service.MessageService;
import com.example.boxchat_java.service.UploadFileService;
import com.example.boxchat_java.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UploadFileService uploadFileService;
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    public UserController(SimpMessagingTemplate messagingTemplate, UserService userService, PasswordEncoder passwordEncoder, UploadFileService uploadFileService, MessageService messageService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.uploadFileService = uploadFileService;
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/login")
    public String getPageLogin() {
        return "page/login";
    }

    @GetMapping("/register")
    public String getPageRegister(Model model) {
        model.addAttribute("user", new User());
        return "page/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("file") MultipartFile file) {
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setAvatar(this.uploadFileService.uploadFile(file, "uploads"));
        this.userService.createUser(user);
        return "redirect:/login";
    }


    @MessageMapping("/chat")
    public void sendMessage(MessageDTO messageDTO) {
        // Gửi riêng cho receiver
        User sender = this.userService.getUserById(messageDTO.getSender()).get();
        User receiver = this.userService.getUserById(messageDTO.getReceiver()).get();
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setText(messageDTO.getText());
        message.setImage(messageDTO.getImage());
        this.messageService.save(message);
        OutputMessage responseDTO = new OutputMessage(message);

//        messagingTemplate.convertAndSendToUser(
//                receiver.getId().toString(),
//                "/queue/messages",
//                responseDTO
//        );
        String chatId = Math.min(sender.getId(), receiver.getId()) + "_" +
                Math.max(sender.getId(), receiver.getId());
        messagingTemplate.convertAndSend("/topic/chat." + chatId, responseDTO);

    }
}
