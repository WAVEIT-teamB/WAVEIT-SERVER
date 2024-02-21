package waveit.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.UserConverter;
import waveit.server.domain.User;

import waveit.server.repository.UserRepository;
import waveit.server.temp.UserIdProvider;
import waveit.server.web.dto.LoginReq;
import waveit.server.web.dto.UserReq;
import waveit.server.web.dto.UserRes;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserIdProvider userIdProvider;

    @Transactional
    public boolean signUpUser(UserReq userReq){
        if (userRepository.existsByLoginId(userReq.getLoginId())){
            return false;
        }
        User user = User.builder()
                .loginId(userReq.getLoginId())
                .name(userReq.getName())
                .phone(userReq.getPhone())
                .email(userReq.getEmail())
                .password(userReq.getPassword())
                .auth(false)
                .introduce(userReq.getIntroduce())
                .build();

        userRepository.save(user);

        return true;
    }

    public User loginUser(LoginReq loginReq){
        return userRepository.findByLoginIdAndPassword(loginReq.getLoginId(), loginReq.getPassword());
    }

    /**
     * 내 정보 확인
     */
    public UserRes getMyInfo() {
        Long userId = userIdProvider.getUserId(); //추후에 로그인된 id로 설정할 예정

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("해당 USER가 없습니다."));

        return UserConverter.convertUserResToDTO(user);

    }

    /**
     * 내 정보 수정
     * @param userReq 정보 수정 Request
     */
    @Transactional
    public UserReq updateMyInfo(UserReq userReq) {
        Long userId = userIdProvider.getUserId(); //추후에 로그인된 id로 설정할 예정

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("해당 USER가 없습니다."));

        user.update(userReq);

        userRepository.save(user);

        return UserConverter.convertUserReqToDTO(user);

    }

}
