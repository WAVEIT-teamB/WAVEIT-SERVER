package waveit.server.temp;

import org.springframework.stereotype.Service;

@Service
public class TempUserIdProvider implements UserIdProvider {
    @Override
    public Long getUserId() {
        // 테스트를 위해 항상 같은 사용자 아이디를 반환합니다.
        return 1L;
    }
}