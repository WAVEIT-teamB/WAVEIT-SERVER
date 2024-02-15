package waveit.server.converter;

import waveit.server.domain.User;
import waveit.server.web.dto.UserReq;
import waveit.server.web.dto.UserRes;

public class UserConverter {
    public static UserRes convertUserResToDTO(User user) {
        UserRes dto = new UserRes();
        dto.setId(user.getId());
        dto.setLoginId(user.getLoginId());
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setIntroduce(user.getIntroduce());
        return dto;
    }

    public static UserReq convertUserReqToDTO(User user) {
        UserReq dto = new UserReq();
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setIntroduce(user.getIntroduce());
        return dto;
    }


}
