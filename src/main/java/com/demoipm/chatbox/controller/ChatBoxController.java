package com.demoipm.chatbox.controller;

import com.demoipm.chatbox.dto.ChatMessage;
import com.demoipm.chatbox.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChatBoxController {

    private final SimpUserRegistry simpUserRegistry;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ChatHistoryService chatHistoryService;

    public ChatBoxController(SimpUserRegistry simpUserRegistry) {
        this.simpUserRegistry = simpUserRegistry;
    }

    @MessageMapping("/chat.addUser")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.sendPrivateMessage")
    @SendToUser("/topic/privateChatRoom")
    public ChatMessage sendPrivateMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        if (!chatMessage.getReceiver().equalsIgnoreCase(chatMessage.getSender())) {
            simpMessagingTemplate.convertAndSendToUser(chatMessage.getReceiver(), "/topic/privateChatRoom", chatMessage);
        }
        chatHistoryService.saveChatHistory(chatMessage);
        return chatMessage;
    }

    @RequestMapping("/get-active-users")
    public ResponseEntity<List<String>> getActiveUserList(@RequestParam("username") String username) throws Exception {
        List<String> activeUserList = this.simpUserRegistry
                .getUsers()
                .stream()
                .map(SimpUser::getName)
                .filter(s -> !s.equalsIgnoreCase(username))
                .collect(Collectors.toList());      
        return new ResponseEntity<>(activeUserList, HttpStatus.OK);
    }

    @RequestMapping("/get-chat-history")
    public ResponseEntity<List<ChatMessage>> getChatHistory(@RequestParam("fromUsername") String fromUsername,
                                                            @RequestParam("toUsername") String toUsername) {
        List<ChatMessage> chatMessages = chatHistoryService.getChatHistoryByUsername(fromUsername, toUsername);
        return new ResponseEntity<>(chatMessages, HttpStatus.OK);
    }
}
