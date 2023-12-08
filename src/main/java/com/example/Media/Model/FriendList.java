package com.example.Media.Model;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "friendList", cascade = CascadeType.ALL)
    private List<Utilisateur> userList;

    @Column(name = "nbr_user")
    private int numberOfUsers;

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

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }
}