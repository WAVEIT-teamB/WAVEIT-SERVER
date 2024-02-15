package waveit.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.UserConverter;
import waveit.server.domain.Portfolio;
import waveit.server.domain.User;
import waveit.server.domain.enums.State;
import waveit.server.global.payload.ApiResponse;

import waveit.server.repository.UserRepository;
import waveit.server.temp.UserIdProvider;
import waveit.server.web.dto.UserDTO;
import waveit.server.web.dto.UserRes;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserIdProvider userIdProvider;

    /**
     * 내 정보 확인
     */
    public UserDTO getMyInfo() {
        Long userId = userIdProvider.getUserId();

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("해당 USER가 없습니다."));

        return UserConverter.convertToDTO(user);

    }

}
