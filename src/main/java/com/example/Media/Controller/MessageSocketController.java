package com.example.Media.Controller;

<<<<<<< HEAD

=======
>>>>>>> master
import com.example.Media.Services.MessageSocketServiceImpl;
import com.example.Media.dto.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
<<<<<<< HEAD
 * Controller class that handles real-time messaging using WebSocket communication.
 * Routes:
 * - /user: Send user conversations to a specific user by their user ID through a web socket.
 * - /conv: Send messages of a specific conversation to the connected users through a web socket.
 * - /sendMessage: Save a new message using a web socket.
 * - /deleteConversation: Delete a conversation by its unique conversation ID using a web socket.
 * - /deleteMessage: Delete a message by its unique message ID within a conversation using a web socket.
=======
 * Controller class that handles real-time messaging using WebSocket
 * communication.
 * Routes:
 * - /user: Send user conversations to a specific user by their user ID through
 * a web socket.
 * - /conv: Send messages of a specific conversation to the connected users
 * through a web socket.
 * - /sendMessage: Save a new message using a web socket.
 * - /deleteConversation: Delete a conversation by its unique conversation ID
 * using a web socket.
 * - /deleteMessage: Delete a message by its unique message ID within a
 * conversation using a web socket.
>>>>>>> master
 */
@RequiredArgsConstructor
@Controller
public class MessageSocketController {
    private final MessageSocketServiceImpl socketService;

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
    @MessageMapping("/user")
    public void sendUserConversationByUserId(int userId) {
        socketService.sendUserConversationByUserId(userId);
    }

    /**
<<<<<<< HEAD
     * Send messages of a specific conversation to the connected users through a web socket.
=======
     * Send messages of a specific conversation to the connected users through a web
     * socket.
>>>>>>> master
     *
     * @param conversationId The ID of the conversation for which to send messages.
     */
    @MessageMapping("/conv")
    public void sendMessagesByConversationId(int conversationId) {
        socketService.sendMessagesByConversationId(conversationId);
    }

    /**
     * Save a new message using a web socket.
     *
<<<<<<< HEAD
     * @param message The MessageRequest object containing the message details to be saved.
=======
     * @param message The MessageRequest object containing the message details to be
     *                saved.
>>>>>>> master
     */
    @MessageMapping("/sendMessage")
    public void saveMessage(MessageRequest message) {
        socketService.saveMessage(message);
    }

    /**
     * Delete a conversation by its unique conversation ID using a web socket.
     *
<<<<<<< HEAD
     * @param payload A Map containing the conversationId, user1Id, and user2Id for the
=======
     * @param payload A Map containing the conversationId, user1Id, and user2Id for
     *                the
>>>>>>> master
     *                conversation to be deleted and notify listener.
     */
    @MessageMapping("/deleteConversation")
    public void deleteConversation(Map<String, Object> payload) {
        int conversationId = (int) payload.get("conversationId");
        int user1 = (int) payload.get("user1Id");
        int user2 = (int) payload.get("user2Id");
        socketService.deleteConversationByConversationId(conversationId);
        socketService.sendUserConversationByUserId(user1);
        socketService.sendUserConversationByUserId(user2);
    }

    /**
<<<<<<< HEAD
     * Delete a message by its unique message ID within a conversation using a web socket.
     *
     * @param payload A Map containing the conversationId and messageId for the message
=======
     * Delete a message by its unique message ID within a conversation using a web
     * socket.
     *
     * @param payload A Map containing the conversationId and messageId for the
     *                message
>>>>>>> master
     *                to be deleted and notify listener.
     */
    @MessageMapping("/deleteMessage")
    public void deleteMessage(Map<String, Object> payload) {
        int conversationId = (int) payload.get("conversationId");
        int messageId = (int) payload.get("messageId");
        socketService.deleteMessageByMessageId(conversationId, messageId);
    }
}
