package waveit.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.PostConverter;
import waveit.server.domain.Post;
import waveit.server.repository.PostRepository;
import waveit.server.web.dto.PostRes;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    public List<PostRes> getUserPosts(Long userId) {
        List<Post> posts = postRepository.findByUser_Id(userId);
        return posts.stream()
                .map(PostConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
