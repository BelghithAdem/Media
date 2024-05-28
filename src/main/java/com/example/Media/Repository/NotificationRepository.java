package com.example.Media.Repository;

import com.example.Media.Model.Comment;
import com.example.Media.Model.Notification;
import com.example.Media.Model.Post;
import com.example.Media.Model.Utilisateur;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
  Optional<Notification> findByReceiverAndOwningPostAndType(Utilisateur receiver, Post owningPost, String type);
  List<Notification> findNotificationsByReceiver(Utilisateur receiver, Pageable pageable);
  List<Notification> findNotificationsByReceiverAndIsSeenIsFalse(Utilisateur receiver);
  List<Notification> findNotificationsByReceiverAndIsReadIsFalse(Utilisateur receiver);
  void deleteNotificationByOwningPost(Post owningPost);
  void deleteNotificationByOwningComment(Comment owningComment);
}
