package waveit.server.converter;

import waveit.server.domain.User;
import waveit.server.web.dto.UserDTO;

public class UserConverter {
    public static UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setLoginId(user.getLoginId());
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setIntroduce(user.getIntroduce());
        return dto;
    }
}
