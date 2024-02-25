package waveit.server.web.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String introduce;

}
