package waveit.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.MemberConverter;
import waveit.server.domain.Member;

import waveit.server.repository.MemberRepository;
import waveit.server.temp.UserIdProvider;
import waveit.server.web.dto.LoginReq;
import waveit.server.web.dto.UserReq;
import waveit.server.web.dto.UserRes;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final UserIdProvider userIdProvider;

    @Transactional
    public boolean signUpUser(UserReq userReq){
        if (memberRepository.existsByLoginId(userReq.getLoginId())){
            return false;
        }
        Member member = Member.builder()
                .loginId(userReq.getLoginId())
                .name(userReq.getName())
                .phone(userReq.getPhone())
                .email(userReq.getEmail())
                .password(userReq.getPassword())
                .auth(false)
                .introduce(userReq.getIntroduce())
                .build();

        memberRepository.save(member);

        return true;
    }

    public Member loginUser(LoginReq loginReq){
        return memberRepository.findByLoginIdAndPassword(loginReq.getLoginId(), loginReq.getPassword());
    }

    /**
     * 내 정보 확인
     */
    public UserRes getMyInfo() {
        Long userId = userIdProvider.getUserId(); //추후에 로그인된 id로 설정할 예정

        Member member = memberRepository.findById(userId).orElseThrow(() -> new IllegalStateException("해당 USER가 없습니다."));

        return MemberConverter.convertUserResToDTO(member);

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

        return MemberConverter.convertUserReqToDTO(member);

    }

}
