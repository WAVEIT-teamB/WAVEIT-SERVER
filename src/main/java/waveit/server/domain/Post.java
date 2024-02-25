package waveit.server.domain;

import jakarta.persistence.*;
import lombok.*;
import waveit.server.domain.common.BaseEntity;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;
import waveit.server.domain.enums.State;
import waveit.server.web.dto.PostReq;
import waveit.server.web.dto.PostRes;
import waveit.server.web.dto.UserReq;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 글 작성자 user id

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition =  "VARCHAR(32)")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition =  "VARCHAR(32)")
    private Part part;

    @Column(nullable = false, length = 256)
    private String title; // 글 제목

    @Column(nullable = false, length = 2048)
    private String description; // 설명

    @Column(nullable = false, length = 64)
    private String cnt;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition =  "VARCHAR(32)")
    private State state;

    public void update(PostReq postReq) {
        this.title = postReq.getTitle();
        this.category = postReq.getCategory();
        this.part = postReq.getPart();
        this.cnt = postReq.getCnt();
        this.description = postReq.getDescription();
    }

    public static Post convertToPostEntity(PostReq postReq) {
        Post post = new Post();
        post.setTitle(postReq.getTitle());
        post.setCategory(postReq.getCategory());
        post.setPart(postReq.getPart());
        post.setDescription(postReq.getDescription());
        post.setCnt(String.valueOf(postReq.getCnt()));
        return post;
    }
}
