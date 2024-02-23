package waveit.server.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waveit.server.domain.Post;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;
import waveit.server.global.payload.ApiResponse;
import waveit.server.service.PostService;
import waveit.server.web.dto.PostRes;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    // 모집 글 작성
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    // 모집 글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    // 모집 글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    // 모집 글 찜하기
    @PatchMapping("/{id}/like")
    public ResponseEntity<?> likePost(@PathVariable Long id, @RequestParam Long userId) {
        postService.likePost(id, userId);
        return ResponseEntity.ok().build();
    }

    // 모집 글 조회 (전체 프로젝트)
    @GetMapping("/projects")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // 모집 글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // 조건에 맞추어 모집 글 검색
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam(required = false) Category category,
                                                  @RequestParam(required = false) Part part) {
        return ResponseEntity.ok(postService.searchPosts(category, part));
    }

    // 지원하기
    @PostMapping("/{id}/apply")
    public ResponseEntity<?> applyToPost(@PathVariable Long id, @RequestParam Long userId, @RequestParam String motivation, @RequestParam String portfolioLink) {
        postService.applyToPost(id, userId, motivation, portfolioLink);
        return ResponseEntity.ok().build();
    }


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
