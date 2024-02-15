package waveit.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.PostConverter;
import waveit.server.domain.Post;
import waveit.server.repository.ApplicationRepository;
import waveit.server.repository.PostRepository;
import waveit.server.web.dto.PostRes;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final ApplicationRepository applicationRepository;


    public List<PostRes> getUserPosts(Long userId) {
        List<Post> posts = postRepository.findByUser_Id(userId);
        return posts.stream()
                .map(post -> {
                    PostRes postRes = PostConverter.convertToDTO(post);
                    Long cnt = applicationRepository.countByPost_Id(post.getId());
                    postRes.setCnt(cnt);
                    return postRes;
                })
                .collect(Collectors.toList());
    }
}
