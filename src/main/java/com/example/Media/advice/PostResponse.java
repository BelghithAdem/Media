package com.example.Media.advice;

<<<<<<< HEAD

=======
>>>>>>> master
import com.example.Media.Model.Post;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Post post;
    private Boolean likedByAuthUser;
}
