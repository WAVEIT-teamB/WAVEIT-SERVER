package waveit.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.MemberConverter;
import waveit.server.domain.Member;

import waveit.server.domain.enums.State;
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
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUpUser(UserReq userReq){
        Member member = Member.builder()
                .loginId(userReq.getLoginId())
                .name(userReq.getName())
                .phone(userReq.getPhone())
                .email(userReq.getEmail())
                .password(passwordEncoder.encode(userReq.getPassword()))
                .auth(false) // 적절한 값으로 설정
                .introduce(userReq.getIntroduce())
                .state(State.ACTIVE) // 적절한 값으로 설정
                .build();

        memberRepository.save(member);
    }

    public boolean checkDuplicateLoginId(String loginId){
        return memberRepository.existsByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public Member loginUser(String loginId, String password){
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디를 가진 사용자를 찾을 수 없습니다."));
        if (!passwordEncoder.matches(password, member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return member;
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
