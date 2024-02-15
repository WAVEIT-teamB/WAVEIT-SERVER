package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waveit.server.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser_Id(Long userId);
}
