package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waveit.server.domain.Likes;
import waveit.server.domain.Post;

import java.util.List;
import java.util.Optional;

public interface LikesRepository  extends JpaRepository<Likes, Long> {

    List<Likes> findByMemberId(Long memberId);
    Optional<Likes> findByPost(Post post);

}

