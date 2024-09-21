package com.example.Media.Services;

import com.example.Media.Model.Utilisateur;
import com.example.Media.advice.VerificationRequest;
import com.example.Media.dto.ApiResponsee;
import com.example.Media.dto.AuthenticationResponse;
import com.example.Media.dto.UpdateUserInfoDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UtilisateurServiceInterface {
  Utilisateur getUserById(Long userId);

  void inscription1(Utilisateur utilisateur);

  void activation(Map<String, String> activation);

  AuthenticationResponse verifyCode(VerificationRequest verificationRequest);

  void ajouterPhotoProfil(Long userId, MultipartFile photo);

  Utilisateur updateUserInfo(Long userId, UpdateUserInfoDto updateUserInfoDto);

  List<Utilisateur> getUserFollowingUsers(Long userId);

  List<Utilisateur> getUserFollowerUsers(Long userId);

  void followUser(Long userId);

  void unfollowUser(Long userId);

  ResponseEntity<ApiResponsee> findConversationIdByUser1IdAndUser2Id(int user1Id, int user2Id);

  ResponseEntity<ApiResponsee> findAllUsersExceptThisUserId(int userId);

  ResponseEntity<ApiResponsee> findAllUsers();

  ResponseEntity<ApiResponsee> findUserByEmail(String email);
}
