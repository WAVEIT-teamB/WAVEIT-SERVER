package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import waveit.server.domain.Post;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;
import waveit.server.web.dto.PostRes;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser_Id(Long userId);

    // Category와 Part에 따라 모집 글 검색
    List<Post> findByCategoryAndPart(Category category, Part part);

}
