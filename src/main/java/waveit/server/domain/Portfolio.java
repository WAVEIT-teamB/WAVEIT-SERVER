package waveit.server.domain;

import jakarta.persistence.*;
import lombok.*;
import waveit.server.domain.common.BaseEntity;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;
import waveit.server.domain.enums.State;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Portfolio extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Column(nullable = false, length = 2048)
    private String link;

}

