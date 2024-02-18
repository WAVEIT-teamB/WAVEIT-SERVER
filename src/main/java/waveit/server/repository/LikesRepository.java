package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waveit.server.domain.Likes;

import java.util.List;

public interface LikesRepository  extends JpaRepository<Likes, Long> {
    List<Likes> findByUserId(Long userId);
}
