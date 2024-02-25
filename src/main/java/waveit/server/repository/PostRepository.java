package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import waveit.server.domain.Post;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;
import waveit.server.web.dto.PostRes;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser_Id(Long userId);

    List<Post> findByCategoryAndPart(Category category, Part part);

}
