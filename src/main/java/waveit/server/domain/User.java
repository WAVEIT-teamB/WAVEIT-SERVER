package waveit.server.domain;

import jakarta.persistence.*;
import lombok.*;
import waveit.server.domain.common.BaseEntity;
import waveit.server.domain.enums.State;
import waveit.server.web.dto.UserReq;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="USER_TABLE")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false, length = 16)
    private String phone;

    @Column(nullable = false, length = 64)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false)
    private Boolean auth;

    @Column(nullable = false, length = 2048)
    private String introduce;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition =  "VARCHAR(32)")
    private State state;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Portfolio> portfolioList = new ArrayList<>();

    public void update(UserReq userReq) {
        this.name = userReq.getName();
        this.phone = userReq.getPhone();
        this.email = userReq.getEmail();
        this.introduce = userReq.getIntroduce();
    }
}
