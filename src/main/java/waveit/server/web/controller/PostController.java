package waveit.server.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import waveit.server.domain.Post;
import waveit.server.global.payload.ApiResponse;
import waveit.server.service.PostService;
import waveit.server.web.dto.PostRes;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserPosts(@PathVariable Long userId) {
        try{
            List<PostRes> posts = postService.getUserPosts(userId);

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(posts)
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
