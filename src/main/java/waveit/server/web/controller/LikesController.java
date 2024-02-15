package waveit.server.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import waveit.server.domain.Post;
import waveit.server.global.payload.ApiResponse;
import waveit.server.service.LikesService;
import waveit.server.service.PortfolioService;
import waveit.server.web.dto.LikesRes;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/likes")
public class LikesController {

    private final LikesService likesService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getLikePostsByUserId(@PathVariable Long userId) {
        try {
            List<LikesRes> likePosts = likesService.findLikedPostsByUserId(userId);

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(likePosts)
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
