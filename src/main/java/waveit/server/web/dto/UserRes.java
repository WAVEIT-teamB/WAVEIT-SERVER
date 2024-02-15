package waveit.server.web.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRes {
    private Long id;
    private String loginId;
    private String name;
    private String phone;
    private String email;
    private String introduce;
}
