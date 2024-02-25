package waveit.server.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waveit.server.domain.Application;
import waveit.server.domain.Portfolio;
import waveit.server.domain.Post;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;
import waveit.server.global.payload.ApiResponse;
import waveit.server.service.PostService;
import waveit.server.web.dto.PortfolioReq;
import waveit.server.web.dto.PostReq;
import waveit.server.web.dto.PostRes;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private final PostService postService;

    // 모집 글 작성
    @PostMapping
    public ResponseEntity<PostRes> createPost(@RequestBody PostReq postReq) {
        PostRes postRes = postService.createPost(postReq);
        return ResponseEntity.ok(postRes);
    }

    //모집 글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostReq postReq) {
        try {
            postReq = postService.updatePost(postId, postReq);
            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(postReq)
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // 모집 글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        try {
            postService.deletePost(postId);

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information("모집글이 삭제되었습니다.")
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 모집 글 찜하기
    @PatchMapping("/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable Long postId) {
        try {
            postService.likePost(postId);
            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information("Post with ID: " + postId + " has been liked.")
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        try {
            List<PostRes> posts = postService.getAllPosts();
            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(posts)
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 모집 글 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        try {
            PostRes postRes = postService.getPostById(postId);
            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(postRes)
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 조건에 맞추어 모집 글 검색
    @GetMapping("/search")
    public ResponseEntity<?> searchPosts(@RequestParam(required = false) Category category,
                                         @RequestParam(required = false) Part part) {
        try {
            List<PostRes> posts = postService.searchPosts(category, part);
            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(posts)
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 지원하기
    @PostMapping("/{postId}/{userId}/apply")
    public ResponseEntity<?> applyToPost(@PathVariable Long postId, @PathVariable Long userId,@RequestBody Application application) {
        try {
            postService.applyToPost(postId,userId, application.getMotivation(), application.getPortfolioLink());
            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information("Application to post with ID: " + postId + " has been submitted.")
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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