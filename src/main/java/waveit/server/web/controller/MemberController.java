package waveit.server.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waveit.server.domain.Member;
import waveit.server.global.payload.ApiResponse;
import waveit.server.service.MemberService;
import waveit.server.web.dto.LoginReq;
import waveit.server.web.dto.UserReq;
import waveit.server.web.dto.UserRes;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody UserReq userReq){
        boolean isSignup = memberService.signUpUser(userReq);
        if(isSignup){
            return ResponseEntity.ok("User signup successfully");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserRes> loginUser(@RequestBody LoginReq loginReq){
        Member member = memberService.loginUser(loginReq);
        if(member != null){
            UserRes userRes = UserRes.builder()
                    .id(member.getId())
                    .loginId(member.getLoginId())
                    .name(member.getName())
                    .phone(member.getPhone())
                    .email(member.getEmail())
                    .introduce(member.getIntroduce())
                    .build();
            return ResponseEntity.ok(userRes);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo() {
        try {
            UserRes userRes = memberService.getMyInfo();

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

            userReq = memberService.updateMyInfo(userReq);

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
