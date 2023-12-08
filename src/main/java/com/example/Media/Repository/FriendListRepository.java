package com.example.Media.Repository;

import com.example.Media.Model.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendListRepository extends JpaRepository<FriendList, Long> {

    // Méthode personnalisée pour récupérer une liste d'amis par un certain critère

}