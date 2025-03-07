package com.example.Media.Services;

<<<<<<< HEAD

=======
>>>>>>> master
import com.example.Media.Model.Conversation;
import com.example.Media.Model.Message;
import com.example.Media.Model.Utilisateur;
import com.example.Media.Repository.ConversationRepository;
import com.example.Media.Repository.MessageRepository;
import com.example.Media.Repository.UtilisateurRespository;
import com.example.Media.dto.ConversationResponse;
import com.example.Media.dto.MessageRequest;
import com.example.Media.dto.MessageResponse;
import com.example.Media.dto.WebSocketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementation of the MessageSocketService interface that handles real-time messaging functionality using web sockets.
 */

/**
<<<<<<< HEAD
 * Implementation of the MessageSocketService interface that handles real-time messaging functionality using web sockets.
 */
@Service
@RequiredArgsConstructor
public class MessageSocketServiceImpl   {
=======
 * Implementation of the MessageSocketService interface that handles real-time
 * messaging functionality using web sockets.
 */
@Service
@RequiredArgsConstructor
public class MessageSocketServiceImpl {
>>>>>>> master
  private final SimpMessagingTemplate messagingTemplate;
  private final UtilisateurRespository userRepository;
  private final ConversationRepository conversationRepository;
  private final MessageRepository messageRepository;

  /**
<<<<<<< HEAD
   * Send user conversations to a specific user by their user ID through a web socket.
=======
   * Send user conversations to a specific user by their user ID through a web
   * socket.
>>>>>>> master
   *
   * @param userId The ID of the user for whom to send conversations.
   */
  public void sendUserConversationByUserId(int userId) {
    List<ConversationResponse> conversation = conversationRepository.findConversationsByUserId(userId);
    messagingTemplate.convertAndSend(
<<<<<<< HEAD
      "/topic/user/".concat(String.valueOf(userId)),
      WebSocketResponse.builder()
        .type("ALL")
        .data(conversation)
        .build()
    );
  }

  /**
   * Send messages of a specific conversation to the connected users through a web socket.
=======
        "/topic/user/".concat(String.valueOf(userId)),
        WebSocketResponse.builder()
            .type("ALL")
            .data(conversation)
            .build());
  }

  /**
   * Send messages of a specific conversation to the connected users through a web
   * socket.
>>>>>>> master
   *
   * @param conversationId The ID of the conversation for which to send messages.
   */
  public void sendMessagesByConversationId(int conversationId) {
    Conversation conversation = new Conversation();
    conversation.setConversationId((long) conversationId);
    List<Message> messageList = messageRepository.findAllByConversation(conversation);
    List<MessageResponse> messageResponseList = messageList.stream()
<<<<<<< HEAD
      .map((message -> MessageResponse.builder()
        .messageId(message.getMessageId())
        .message(message.getMessage())
        .timestamp(Date.from(message.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()))
        .senderId(message.getSender().getUserId())
        .receiverId(message.getReceiver().getUserId())
        .build())
      ).toList();
    messagingTemplate.convertAndSend("/topic/conv/".concat(String.valueOf(conversationId)), WebSocketResponse.builder()
      .type("ALL")
      .data(messageResponseList)
      .build()
    );
=======
        .map((message -> MessageResponse.builder()
            .messageId(message.getMessageId())
            .message(message.getMessage())
            .timestamp(Date.from(message.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()))
            .senderId(message.getSender().getUserId())
            .receiverId(message.getReceiver().getUserId())
            .build()))
        .toList();
    messagingTemplate.convertAndSend("/topic/conv/".concat(String.valueOf(conversationId)), WebSocketResponse.builder()
        .type("ALL")
        .data(messageResponseList)
        .build());
>>>>>>> master
  }

  /**
   * Save a new message using a web socket.
   *
<<<<<<< HEAD
   * @param msg The MessageRequest object containing the message details to be saved.
=======
   * @param msg The MessageRequest object containing the message details to be
   *            saved.
>>>>>>> master
   */
  public void saveMessage(MessageRequest msg) {
    // Convertir les identifiants de l'expéditeur et du destinataire en Long
    Integer senderId = msg.getSenderId();
    Integer receiverId = msg.getReceiverId();

<<<<<<< HEAD
    // Recherche de l'expéditeur et du destinataire dans le référentiel d'utilisateurs
    Utilisateur sender = userRepository.findById(Long.valueOf(senderId))
      .orElseThrow(() -> new NoSuchElementException("Utilisateur with ID " + senderId + " not found"));
    Utilisateur receiver = userRepository.findById(Long.valueOf(receiverId))
      .orElseThrow(() -> new NoSuchElementException("Utilisateur with ID " + receiverId + " not found"));
    Conversation conversation = conversationRepository.findConversationByUsers(sender, receiver)
      .orElseThrow(() -> new NoSuchElementException("Conversation not found"));
=======
    // Recherche de l'expéditeur et du destinataire dans le référentiel
    // d'utilisateurs
    Utilisateur sender = userRepository.findById(Long.valueOf(senderId))
        .orElseThrow(() -> new NoSuchElementException("Utilisateur with ID " + senderId + " not found"));
    Utilisateur receiver = userRepository.findById(Long.valueOf(receiverId))
        .orElseThrow(() -> new NoSuchElementException("Utilisateur with ID " + receiverId + " not found"));
    Conversation conversation = conversationRepository.findConversationByUsers(sender, receiver)
        .orElseThrow(() -> new NoSuchElementException("Conversation not found"));
>>>>>>> master

    Message newMessage = new Message();
    newMessage.setMessage(msg.getMessage());
    newMessage.setTimestamp(msg.getTimestamp());
    newMessage.setConversation(conversation);
    newMessage.setSender(sender);
    newMessage.setReceiver(receiver);

    Message savedMessage = messageRepository.save(newMessage);

    // notify listener
    MessageResponse res = MessageResponse.builder()
<<<<<<< HEAD
      .messageId(savedMessage.getMessageId())
      .message(savedMessage.getMessage())
      .timestamp(Date.from(savedMessage.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()))
      .senderId(savedMessage.getSender().getUserId())
      .receiverId(savedMessage.getReceiver().getUserId())
      .build();

    messagingTemplate.convertAndSend("/topic/conv/".concat(msg.getConversationId().toString()),
      WebSocketResponse.builder()
        .type("ADDED")
        .data(res)
        .build()
    );
=======
        .messageId(savedMessage.getMessageId())
        .message(savedMessage.getMessage())
        .timestamp(Date.from(savedMessage.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()))
        .senderId(savedMessage.getSender().getUserId())
        .receiverId(savedMessage.getReceiver().getUserId())
        .build();

    messagingTemplate.convertAndSend("/topic/conv/".concat(msg.getConversationId().toString()),
        WebSocketResponse.builder()
            .type("ADDED")
            .data(res)
            .build());
>>>>>>> master

    sendUserConversationByUserId(Math.toIntExact(msg.getSenderId()));
    sendUserConversationByUserId(Math.toIntExact(msg.getReceiverId()));
  }

  /**
   * Delete a conversation by its unique conversation ID using a web socket.
   *
   * @param conversationId The ID of the conversation to be deleted.
   */
  @Transactional
  public void deleteConversationByConversationId(int conversationId) {
    Conversation c = new Conversation();
    c.setConversationId((long) conversationId);
    messageRepository.deleteAllByConversation(c);
    conversationRepository.deleteById((long) conversationId);
  }

  /**
<<<<<<< HEAD
   * Delete a message by its unique message ID within a conversation using a web socket.
=======
   * Delete a message by its unique message ID within a conversation using a web
   * socket.
>>>>>>> master
   *
   * @param conversationId The ID of the conversation to notify its listener.
   * @param messageId      The ID of the message to be deleted.
   */
  public void deleteMessageByMessageId(int conversationId, int messageId) {
    messageRepository.deleteById((long) messageId);
    // notify listener
    sendMessagesByConversationId(conversationId);
  }
}
