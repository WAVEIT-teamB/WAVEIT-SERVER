package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waveit.server.domain.Likes;
import waveit.server.domain.Post;
import waveit.server.domain.User;

import java.util.List;
import java.util.Optional;

public interface LikesRepository  extends JpaRepository<Likes, Long> {
    List<Likes> findByUserId(Long userId);

    // 특정 사용자가 특정 게시물에 '좋아요'를 눌렀는지 확인하는 메서드
    Optional<Likes> findByUserAndPost(User user, Post post);

    // 사용자가 '좋아요'한 모든 게시물을 찾는 메서드
    List<Likes> findByUser(User user);

}
