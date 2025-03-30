package com.hj_seed.seed_back_v1.domain.post.controller;

import com.hj_seed.seed_back_v1.common.data.dto.ItemsResponseDto;
import com.hj_seed.seed_back_v1.common.exception.PostException;
import com.hj_seed.seed_back_v1.common.exception.message.ExceptionMessage;
import com.hj_seed.seed_back_v1.domain.post.data.entity.Post;
import com.hj_seed.seed_back_v1.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "Post APIs", description = "게시글 관련 API 목록")
public class PostController {

    private final PostService postService;

    @GetMapping()
    @Operation(summary = "게시글 목록 조회", description = "전체 게시글의 목록을 조회한다.")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호", example = "1"),
            @Parameter(name = "numOfRows", description = "한 페이지 결과 수", example = "10")
    })
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json")
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = DepartmentsResponseDto.class)
//                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<ItemsResponseDto<Post>> getPosts(@RequestParam int page,
                                                                 @RequestParam int numOfRows) {

        int totalCount = postService.getTotalCount();
        List<Post> posts = postService.getPosts(page, numOfRows);

        if (!posts.isEmpty()) {
            return ResponseEntity.ok(
                    new ItemsResponseDto<>(HttpStatus.OK, posts, page, totalCount)
            );
        } else {
            // JSON 형태로 응답을 보낸 필요가 없을 때 아래와 같이 작성한다.
            // return ResponseEntity.noContent().build();
            // 상태 코드가 204일 때는 본문을 포함할 수 없기 때문에 JSON 형태로 응답이 필요할 때는 아래와 같이 작성한다.
            // return ResponseEntity.ok(
            //         new DepartmentsResponseDto(HttpStatus.NO_CONTENT, departments, page, totalCount)
            // );
            throw new PostException(ExceptionMessage.DEPARTMENT_NOT_FOUND);
        }
    }
}
