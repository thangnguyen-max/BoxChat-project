package com.example.boxchat_java.controller;

import com.example.boxchat_java.domain.Message;
import com.example.boxchat_java.domain.User;
import com.example.boxchat_java.service.MessageService;
import com.example.boxchat_java.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class ChatController {
    private final UserService userService;
    private final MessageService messageService;

    public ChatController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public ModelAndView getPageHome(Model model, HttpServletRequest request, @RequestParam(value = "keyword", required = false) String keyword) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("user", users.get(0));
        model.addAttribute("users", users);
        List<User> user = userService.findByKeyword(keyword);
        model.addAttribute("userSearch", user);
        return new ModelAndView("page/home");
    }


    @PostMapping("/chat/select/{id}")
    public ResponseEntity<List<Message>> selectChat(@PathVariable Long id, HttpSession session, Model model) {
        long receiverId = id;
        long senderId = (long) session.getAttribute("id");
        List<Message> chat = this.messageService.findOneChat(senderId,receiverId,receiverId,senderId);
        User sender = this.userService.getUserById(senderId).get();
        User receiver = this.userService.getUserById(receiverId).get();
        // Ở đây bạn có thể lấy lịch sử chat từ DB
        model.addAttribute("sender", sender);
        model.addAttribute("receiver", receiver);
        // Trả về JSON hoặc HTML fragment
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
}
