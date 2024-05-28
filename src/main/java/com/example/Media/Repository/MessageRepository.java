package com.example.Media.Repository;


import com.example.Media.Model.Conversation;
import com.example.Media.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByConversation(Conversation conversation);

    void deleteAllByConversation(Conversation conversation);
}
