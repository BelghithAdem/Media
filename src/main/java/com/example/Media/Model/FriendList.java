package com.example.Media.Model;
<<<<<<< HEAD
=======

>>>>>>> master
import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "friend_list")
public class FriendList {

<<<<<<< HEAD
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "friendList", cascade = CascadeType.PERSIST)
  private List<Utilisateur> userList;
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "friendList", cascade = CascadeType.PERSIST)
    private List<Utilisateur> userList;
>>>>>>> master

    // Getters and setters
    // ...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Utilisateur> getUserList() {
        return userList;
    }

    public void setUserList(List<Utilisateur> userList) {
        this.userList = userList;
    }

<<<<<<< HEAD



=======
>>>>>>> master
}
