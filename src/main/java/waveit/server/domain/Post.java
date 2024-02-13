package waveit.server.domain;

import jakarta.persistence.*;
import lombok.*;
import waveit.server.domain.common.BaseEntity;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;
import waveit.server.domain.enums.State;

@Entity
@Getter
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
    private String contact;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition =  "VARCHAR(32)")
    private State state;

}
