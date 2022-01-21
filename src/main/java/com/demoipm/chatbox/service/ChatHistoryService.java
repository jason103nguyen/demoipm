package com.demoipm.chatbox.service;

import com.demoipm.chatbox.dto.ChatMessage;

import java.util.List;

public interface ChatHistoryService {

    List<ChatMessage> getChatHistoryByUsername(String fromUsername, String toUsername);

    void saveChatHistory(ChatMessage chatMessage);
}
