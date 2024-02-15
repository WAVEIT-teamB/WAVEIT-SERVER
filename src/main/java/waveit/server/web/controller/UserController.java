package waveit.server.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waveit.server.global.payload.ApiResponse;
import waveit.server.service.UserService;
import waveit.server.web.dto.UserReq;
import waveit.server.web.dto.UserRes;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo() {
        try {
            UserRes userRes = userService.getMyInfo();

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(userRes)
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateMyInfo(@RequestBody UserReq userReq) {
        try {

            userReq = userService.updateMyInfo(userReq);

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(userReq)
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
