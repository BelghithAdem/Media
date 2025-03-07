package com.example.Media.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.*;

import jakarta.persistence.*;

import java.util.List;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String type;

  @OneToOne
  @JoinColumn(name = "receiver_id")
  private Utilisateur receiver;

  @OneToOne
  @JoinColumn(name = "sender_id")
  private Utilisateur sender;

  @OneToOne
  @JoinColumn(name = "owning_post_id")
  private Post owningPost;

  @OneToOne
  @JoinColumn(name = "owning_comment_id")
  private Comment owningComment;

  private Boolean isSeen;
  private Boolean isRead;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date dateCreated;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date dateUpdated;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date dateLastModified;

  @Override
  public boolean equals(Object o) {
<<<<<<< HEAD
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Notification that = (Notification) o;
    return id.equals(that.id) && type.equals(that.type) && receiver.equals(that.receiver) && owningPost.equals(that.owningPost);
=======
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Notification that = (Notification) o;
    return id.equals(that.id) && type.equals(that.type) && receiver.equals(that.receiver)
        && owningPost.equals(that.owningPost);
>>>>>>> master
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, receiver, owningPost);
  }
}
