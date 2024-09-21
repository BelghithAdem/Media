package com.example.Media.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

<<<<<<< HEAD


=======
>>>>>>> master
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    @Size(max = 4096)
    private String content;
}
