package com.hj_seed.seed_back_v1.domain.post.data.dto;

import com.hj_seed.seed_back_v1.domain.category.data.entity.Category;
import com.hj_seed.seed_back_v1.domain.post.data.entity.Post;
import com.hj_seed.seed_back_v1.domain.user.data.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PostRequestDto {
    @Schema(description = "게시글 제목", example = "")
    @NotBlank
    private final String title;

    @Schema(description = "게시글 내용", example = "")
    private final String content;

    @Schema(description = "user_id", example = "")
//    @Pattern(regexp = "Y|N", message = "'Y' 또는 'N'이어야 한다.")
    private final String user_id;

    @Schema(description = "정원", example = "50")
//    @PositiveOrZero
    private final int category;

    public Post toPost() {
        return Post.builder()
                .title(title)
                .content(content)
                .user(new User())
                .category(new Category())
                .build();
    }
}
