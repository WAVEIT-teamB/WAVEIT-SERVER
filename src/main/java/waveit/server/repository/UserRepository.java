package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waveit.server.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
