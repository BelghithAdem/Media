package com.example.Media.Controller;

import com.example.Media.Services.UtilisateurService;
import com.example.Media.dto.ApiResponsee;

import com.example.Media.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class that handles user-related HTTP requests and interactions.
 * Routes:
 * - POST /user/register: Register a new user in the system.
 * - POST /user/login: Login a user based on their email address.
 * - GET /user/all: Retrieve a list of all users in the system.
<<<<<<< HEAD
 * - GET /user/except/{userId}: Retrieve a list of all users except the user with a specific user ID.
 * - GET /user/conversation/id: Find or create a conversation ID for a pair of users based on their user IDs.
=======
 * - GET /user/except/{userId}: Retrieve a list of all users except the user
 * with a specific user ID.
 * - GET /user/conversation/id: Find or create a conversation ID for a pair of
 * users based on their user IDs.
>>>>>>> master
 */
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    final UtilisateurService userService;

    /**
     * Register a new user in the system.
     *
     * @param user The User object representing the user to be registered.
<<<<<<< HEAD
     * @return ResponseEntity containing an ApiResponse indicating the result of the registration.
=======
     * @return ResponseEntity containing an ApiResponse indicating the result of the
     *         registration.
>>>>>>> master
     */

    /**
     * Login a user based on their email address.
     *
<<<<<<< HEAD
     * @param loginRequest The LoginRequest object containing the user's email for login.
     * @return ResponseEntity containing an ApiResponse with user information if the login is successful.
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponsee> login(@RequestBody LoginRequest loginRequest) {
      return userService.findUserByEmail(loginRequest.getEmail());
=======
     * @param loginRequest The LoginRequest object containing the user's email for
     *                     login.
     * @return ResponseEntity containing an ApiResponse with user information if the
     *         login is successful.
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponsee> login(@RequestBody LoginRequest loginRequest) {
        return userService.findUserByEmail(loginRequest.getEmail());
>>>>>>> master
    }

    /**
     * Retrieve a list of all users in the system.
     *
<<<<<<< HEAD
     * @return ResponseEntity containing an ApiResponse with a list of User objects representing all users.
=======
     * @return ResponseEntity containing an ApiResponse with a list of User objects
     *         representing all users.
>>>>>>> master
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponsee> findAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * Retrieve a list of all users except the user with a specific user ID.
     *
     * @param userId The ID of the user to be excluded from the list.
<<<<<<< HEAD
     * @return ResponseEntity containing an ApiResponse with a list of User objects representing all users except the specified user.
=======
     * @return ResponseEntity containing an ApiResponse with a list of User objects
     *         representing all users except the specified user.
>>>>>>> master
     */
    @GetMapping("/except/{userId}")
    public ResponseEntity<ApiResponsee> findAllUsersExceptThisUserId(@PathVariable int userId) {
        return userService.findAllUsersExceptThisUserId(userId);
    }

    /**
     * Find or create a conversation ID for a pair of users based on their user IDs.
     *
     * @param user1Id The ID of the first user in the conversation.
     * @param user2Id The ID of the second user in the conversation.
<<<<<<< HEAD
     * @return ResponseEntity containing an ApiResponse with the conversation ID for the user pair.
     */
    @GetMapping("/conversation/id")
    public ResponseEntity<ApiResponsee> findConversationIdByUser1IdAndUser2Id(@RequestParam int user1Id, @RequestParam int user2Id) {
=======
     * @return ResponseEntity containing an ApiResponse with the conversation ID for
     *         the user pair.
     */
    @GetMapping("/conversation/id")
    public ResponseEntity<ApiResponsee> findConversationIdByUser1IdAndUser2Id(@RequestParam int user1Id,
            @RequestParam int user2Id) {
>>>>>>> master
        return userService.findConversationIdByUser1IdAndUser2Id(user1Id, user2Id);
    }
}
