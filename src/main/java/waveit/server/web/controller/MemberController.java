package waveit.server.web.controller;

import lombok.RequiredArgsConstructor;
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

    /*
    회원가입
     */
    @PostMapping("/signup")
    public String signupUser(@RequestBody UserReq userReq){
            memberService.signUpUser(userReq);
        return "redirect:/login";
        }


    /*
    아이디 중복 확인
     */
    @PostMapping("/checkduplicate")
    public String checkDuplicateLoginId(@RequestBody UserReq userReq){
        String loginId = userReq.getLoginId();
        boolean isDuplicate = memberService.checkDuplicateLoginId(loginId);
        if (isDuplicate){
            return "중복된 아이디입니다.";
        } else {
            return "사용 가능한 아이디입니다.";
        }
    }

    /*
    로그인
     */
    @PostMapping("/login")
    public Member loginUser(@RequestBody LoginReq loginReq){
        String loginId = loginReq.getLoginId();
        String password = loginReq.getPassword();
        return memberService.loginUser(loginId, password);
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
