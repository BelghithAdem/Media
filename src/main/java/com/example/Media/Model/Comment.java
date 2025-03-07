package com.example.Media.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "comments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 1024)
  private String content;
  private Integer likeCount;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date dateCreated;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date dateLastModified;

  @OneToOne
  @JoinColumn(name = "author_id", nullable = false)
  private Utilisateur author;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @JsonIgnore
  @ManyToMany
<<<<<<< HEAD
  @JoinTable(
    name = "comment_likes",
    joinColumns = @JoinColumn(name = "comment_id"),
    inverseJoinColumns = @JoinColumn(name = "liker_id")
  )
=======
  @JoinTable(name = "comment_likes", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "liker_id"))
>>>>>>> master
  private List<Utilisateur> likeList = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
<<<<<<< HEAD
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
=======
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
>>>>>>> master
    Comment comment = (Comment) o;
    return Objects.equals(id, comment.id) && Objects.equals(author, comment.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, author);
  }
}
