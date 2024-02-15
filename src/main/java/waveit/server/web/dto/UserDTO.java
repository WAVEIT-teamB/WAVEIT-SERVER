package waveit.server.web.dto;

import lombok.*;
import waveit.server.domain.Portfolio;
import waveit.server.domain.enums.State;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String loginId;
    private String name;
    private String phone;
    private String email;
    private String introduce;
}
