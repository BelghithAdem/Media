package com.example.Media.dto;

import java.sql.Timestamp;

public interface ConversationResponse {

  Long getConversationId();

  Long getOtherUserId();

  String getOtherUserName();

  String getLastMessage();

  Timestamp getLastMessageTimestamp();
}
