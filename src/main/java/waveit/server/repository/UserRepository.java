package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waveit.server.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자를 이메일 또는 로그인 ID로 찾는 메서드 예시
    Optional<User> findByEmail(String email);
    Optional<User> findByLoginId(String loginId);
}
