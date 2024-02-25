package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waveit.server.domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Long countByPost_Id(Long postId);
}
