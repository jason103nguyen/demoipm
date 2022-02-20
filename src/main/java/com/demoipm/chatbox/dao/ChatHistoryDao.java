package com.demoipm.chatbox.dao;

import com.demoipm.chatbox.entity.ChatMessageHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatHistoryDao extends JpaRepository<ChatMessageHistory, Integer> {

    @Query("SELECT c FROM chat_history c WHERE " +
            "(c.sender = :fromUsername AND c.receiver = :toUsername) OR " +
            "(c.sender = :toUsername AND c.receiver = :fromUsername)")
    List<ChatMessageHistory> getChatMessageRelatedToUsername(@Param("fromUsername") String fromUsername, @Param("toUsername") String toUsername, Sort sort);
}
