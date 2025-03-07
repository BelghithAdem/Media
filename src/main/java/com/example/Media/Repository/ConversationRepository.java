package com.example.Media.Repository;

import com.example.Media.Model.Conversation;
import com.example.Media.Model.Utilisateur;
import com.example.Media.dto.ConversationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

<<<<<<< HEAD

  @Query("SELECT c FROM Conversation c WHERE (c.user1 = :user1 AND c.user2 = :user2) OR (c.user1 = :user2 AND c.user2 = :user1)")
  Optional<Conversation> findConversationByUsers(@Param("user1") Utilisateur user1, @Param("user2") Utilisateur user2);

  @Query(
    nativeQuery = true,
    value = """
                    SELECT
                        C.conversation_id AS conversationId,
                        U.user_id AS otherUserId,
                        CONCAT(U.first_name, ' ', U.last_name) AS otherUserName,
                        M.message AS lastMessage,
                        M.timestamp AS lastMessageTimestamp
                    FROM conversation AS C

                    INNER JOIN utilisateur AS U
                    ON (C.user1_id = U.user_id OR C.user2_id = U.user_id) AND U.user_id != ?1

                    LEFT JOIN (
                        SELECT
                            conversation_id,
                            (SELECT message FROM message M2 WHERE M2.conversation_id = M.conversation_id ORDER BY M2.timestamp DESC LIMIT 1) AS message,
                            MAX(timestamp) AS timestamp
                        FROM message M
                        GROUP BY conversation_id
                    ) AS M
                    ON C.conversation_id = M.conversation_id

                    WHERE C.user1_id = ?1 OR C.user2_id = ?1
                    ORDER BY M.timestamp DESC;
                    """
  )
  List<ConversationResponse> findConversationsByUserId(int userId);


}

=======
  @Query("SELECT c FROM Conversation c WHERE (c.user1 = :user1 AND c.user2 = :user2) OR (c.user1 = :user2 AND c.user2 = :user1)")
  Optional<Conversation> findConversationByUsers(@Param("user1") Utilisateur user1, @Param("user2") Utilisateur user2);

  @Query(nativeQuery = true, value = """
      SELECT
          C.conversation_id AS conversationId,
          U.user_id AS otherUserId,
          CONCAT(U.first_name, ' ', U.last_name) AS otherUserName,
          M.message AS lastMessage,
          M.timestamp AS lastMessageTimestamp
      FROM conversation AS C

      INNER JOIN utilisateur AS U
      ON (C.user1_id = U.user_id OR C.user2_id = U.user_id) AND U.user_id != ?1

      LEFT JOIN (
          SELECT
              conversation_id,
              (SELECT message FROM message M2 WHERE M2.conversation_id = M.conversation_id ORDER BY M2.timestamp DESC LIMIT 1) AS message,
              MAX(timestamp) AS timestamp
          FROM message M
          GROUP BY conversation_id
      ) AS M
      ON C.conversation_id = M.conversation_id

      WHERE C.user1_id = ?1 OR C.user2_id = ?1
      ORDER BY M.timestamp DESC;
      """)
  List<ConversationResponse> findConversationsByUserId(int userId);

}
>>>>>>> master
