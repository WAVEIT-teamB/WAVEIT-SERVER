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
    public ResponseEntity<String> checkDuplicateLoginId(@RequestBody UserReq userReq){
        String loginId = userReq.getLoginId();
        boolean isDuplicate = memberService.checkDuplicateLoginId(loginId);
        if (isDuplicate){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 아이디입니다.");
        } else {
            return ResponseEntity.ok().body("사용 가능한 아이디입니다.");
        }
    }

    /*
    로그인
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginReq loginReq){
        try{
            String loginId = loginReq.getLoginId();
            String password = loginReq.getPassword();
            Member member = memberService.loginUser(loginId, password);
            return ResponseEntity.ok(member);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 과정에서 오류가 발생했습니다.");
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
