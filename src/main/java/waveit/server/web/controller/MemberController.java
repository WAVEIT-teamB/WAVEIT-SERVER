package waveit.server.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waveit.server.domain.User;
import waveit.server.global.payload.ApiResponse;
import waveit.server.service.UserService;
import waveit.server.web.dto.LoginReq;
import waveit.server.web.dto.UserReq;
import waveit.server.web.dto.UserRes;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody UserReq userReq){
        boolean isSignup = userService.signUpUser(userReq);
        if(isSignup){
            return ResponseEntity.ok("User signup successfully");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserRes> loginUser(@RequestBody LoginReq loginReq){
        User user = userService.loginUser(loginReq);
        if(user != null){
            UserRes userRes = UserRes.builder()
                    .id(user.getId())
                    .loginId(user.getLoginId())
                    .name(user.getName())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .introduce(user.getIntroduce())
                    .build();
            return ResponseEntity.ok(userRes);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


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
