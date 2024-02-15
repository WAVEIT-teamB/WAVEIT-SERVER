package waveit.server.web.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import waveit.server.converter.UserConverter;
import waveit.server.domain.User;
import waveit.server.global.payload.ApiResponse;
import waveit.server.service.UserService;
import waveit.server.web.dto.UserDTO;

import java.nio.file.attribute.UserPrincipal;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo() {
        try {
            UserDTO userDTO = userService.getMyInfo();

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(userDTO)
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
