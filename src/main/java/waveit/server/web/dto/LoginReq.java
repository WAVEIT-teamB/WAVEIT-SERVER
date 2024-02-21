package waveit.server.web.dto;

import lombok.Data;

@Data
public class LoginReq {
    private String loginId;
    private String password;
}
