package waveit.server.converter;

import waveit.server.domain.Member;
import waveit.server.web.dto.UserReq;
import waveit.server.web.dto.UserRes;

public class MemberConverter {
    public static UserRes convertUserResToDTO(Member member) {
        UserRes dto = new UserRes();
        dto.setId(member.getId());
        dto.setLoginId(member.getLoginId());
        dto.setName(member.getName());
        dto.setPhone(member.getPhone());
        dto.setEmail(member.getEmail());
        dto.setIntroduce(member.getIntroduce());
        return dto;
    }

    public static UserReq convertUserReqToDTO(Member member) {
        UserReq dto = new UserReq();
        dto.setName(member.getName());
        dto.setPhone(member.getPhone());
        dto.setEmail(member.getEmail());
        dto.setIntroduce(member.getIntroduce());
        return dto;
    }


}
