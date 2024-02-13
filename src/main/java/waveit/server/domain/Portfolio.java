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
public class Portfolio extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 2048)
    private String content;

}

