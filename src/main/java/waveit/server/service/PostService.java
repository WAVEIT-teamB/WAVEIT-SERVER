package waveit.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.PostConverter;
import waveit.server.domain.Application;
import waveit.server.domain.Likes;
import waveit.server.domain.Post;
import waveit.server.domain.User;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;
import waveit.server.repository.ApplicationRepository;
import waveit.server.repository.LikesRepository;
import waveit.server.repository.PostRepository;
import waveit.server.repository.UserRepository;
import waveit.server.web.dto.PostRes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final ApplicationRepository applicationRepository;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikesRepository likesRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id).map(post -> {
            post.setTitle(updatedPost.getTitle());
            post.setDescription(updatedPost.getDescription());
            post.setContact(updatedPost.getContact());
            post.setCategory(updatedPost.getCategory());
            post.setPart(updatedPost.getPart());
            post.setState(updatedPost.getState());
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

    @Transactional
    public void likePost(Long postId, Long userId) {
        User user = (User) userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + postId));

        Optional<Likes> existingLike = likesRepository.findByUserAndPost(user, post);
        if (existingLike.isPresent()) {
            likesRepository.delete(existingLike.get());
        } else {
            Likes newLike = Likes.builder()
                    .user(user)
                    .post(post)
                    .build(); // 빌더 패턴 사용
            likesRepository.save(newLike);
        }
    }


    @Transactional(readOnly = true)
    public List<Post> searchPosts(Category category, Part part) {
        return postRepository.findByCategoryAndPart(category, part);
    }


    public Application applyToPost(Long postId, Long userId, String motivation, String portfolioLink) {
        User user = (User) userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + postId));

        Application application = Application.builder()
                .user(user)
                .post(post)
                .motivation(motivation)
                .portfolioLink(portfolioLink)
                .build();
        return applicationRepository.save(application);
    }

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
