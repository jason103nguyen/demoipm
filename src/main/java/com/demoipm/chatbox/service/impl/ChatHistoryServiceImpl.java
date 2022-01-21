package com.demoipm.chatbox.service.impl;

import com.demoipm.chatbox.dao.ChatHistoryDao;
import com.demoipm.chatbox.dto.ChatMessage;
import com.demoipm.chatbox.entity.ChatMessageHistory;
import com.demoipm.chatbox.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {

    @Autowired
    private ChatHistoryDao chatHistoryDao;

    @Override
    public List<ChatMessage> getChatHistoryByUsername(String fromUsername, String toUsername) {
        List<ChatMessageHistory> chatMessageHistories = chatHistoryDao.getChatMessageRelatedToUsername(fromUsername, toUsername, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<ChatMessage> chatMessages = chatMessageHistories.stream().map(chatMessageHistory -> {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setSender(chatMessageHistory.getSender());
            chatMessage.setReceiver(chatMessageHistory.getReceiver());
            chatMessage.setContent(chatMessageHistory.getContent());
            chatMessage.setType(ChatMessage.MessageType.valueOf(chatMessageHistory.getType()));
            return chatMessage;
        }).collect(Collectors.toList());
        return chatMessages;
    }

    @Override
    public void saveChatHistory(ChatMessage chatMessage) {
        ChatMessageHistory chatMessageHistory = new ChatMessageHistory();
        chatMessageHistory.setSender(chatMessage.getSender());
        chatMessageHistory.setReceiver(chatMessage.getReceiver());
        chatMessageHistory.setContent(chatMessage.getContent());
        chatMessageHistory.setType(chatMessage.getType().name());
        chatHistoryDao.save(chatMessageHistory);
    }
}
