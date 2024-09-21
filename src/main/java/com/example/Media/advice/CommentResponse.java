package com.example.Media.advice;

<<<<<<< HEAD

=======
>>>>>>> master
import com.example.Media.Model.Comment;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Comment comment;
    private Boolean likedByAuthUser;
}
