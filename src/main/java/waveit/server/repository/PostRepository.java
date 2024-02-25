package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waveit.server.domain.Post;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMemberId(Long memberId);

    List<Post> findByCategoryAndPart(Category category, Part part);

}
