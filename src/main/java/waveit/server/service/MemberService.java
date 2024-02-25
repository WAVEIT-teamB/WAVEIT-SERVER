package waveit.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.UserConverter;
import waveit.server.domain.Member;

import waveit.server.repository.MemberRepository;
import waveit.server.temp.UserIdProvider;
import waveit.server.web.dto.UserReq;
import waveit.server.web.dto.UserRes;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final UserIdProvider userIdProvider;

    /**
     * 내 정보 확인
     */
    public UserRes getMyInfo() {
        Long userId = userIdProvider.getUserId(); //추후에 로그인된 id로 설정할 예정

        Member member = memberRepository.findById(userId).orElseThrow(() -> new IllegalStateException("해당 USER가 없습니다."));

        return UserConverter.convertUserResToDTO(member);

    }

    /**
     * 내 정보 수정
     * @param userReq 정보 수정 Request
     */
    @Transactional
    public UserReq updateMyInfo(UserReq userReq) {
        Long userId = userIdProvider.getUserId(); //추후에 로그인된 id로 설정할 예정

        Member member = memberRepository.findById(userId).orElseThrow(() -> new IllegalStateException("해당 USER가 없습니다."));

        member.update(userReq);

        memberRepository.save(member);

        return UserConverter.convertUserReqToDTO(member);

    }

}
