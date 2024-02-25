package waveit.server.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.PostConverter;
import waveit.server.domain.Application;
import waveit.server.domain.Likes;
import waveit.server.domain.Post;
import waveit.server.domain.Member;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;
import waveit.server.repository.ApplicationRepository;
import waveit.server.repository.LikesRepository;
import waveit.server.repository.PostRepository;
import waveit.server.repository.MemberRepository;
import waveit.server.temp.UserIdProvider;
import waveit.server.web.dto.PostReq;
import waveit.server.web.dto.PostRes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final LikesRepository likesRepository;
    private final ApplicationRepository applicationRepository;


    private final UserIdProvider userIdProvider;


    public PostRes createPost(PostReq postReq) {
        Post post = Post.convertToPostEntity(postReq);
        post = postRepository.save(post);
        return PostConverter.convertPostResToDTO(post);
    }

    @Transactional
    public PostReq updatePost(Long postId, PostReq postReq) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        post.update(postReq);
        postRepository.save(post);
        return PostConverter.convertPostReqToDTO(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + postId));
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public List<PostRes> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> PostConverter.convertPostResToDTO(post)) // Adjust countByPost accordingly
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public PostRes getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found" + postId));
        return PostConverter.convertPostResToDTO(post);
    }

    @Transactional
    public void likePost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + postId));

        Optional<Likes> existingLike = likesRepository.findByPost(post);

        if (existingLike.isPresent()) {
            likesRepository.delete(existingLike.get());
        } else {
            Likes newLike = Likes.builder()
                    .post(post)
                    .build();
            likesRepository.save(newLike);
        }
    }


    @Transactional(readOnly = true)
    public List<PostRes> searchPosts(Category category, Part part) {
        List<Post> posts = postRepository.findByCategoryAndPart(category, part);
        return posts.stream()
                .map(post -> PostConverter.convertPostResToDTO(post))
                .collect(Collectors.toList());
    }


    public Application applyToPost(Long postId, Long userId, String motivation, String portfolioLink) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + postId));

        Application application = Application.builder()
                .member(member)
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
                    PostRes postRes = PostConverter.convertPostResToDTO(post);
                    String cnt = String.valueOf(applicationRepository.countByPost_Id(post.getId()));
                    postRes.setCnt(cnt);
                    return postRes;
                })
                .collect(Collectors.toList());
    }



}

