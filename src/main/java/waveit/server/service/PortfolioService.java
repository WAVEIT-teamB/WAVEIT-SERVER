package waveit.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.PortfolioConverter;
import waveit.server.domain.Portfolio;
import waveit.server.domain.Member;
import waveit.server.repository.PortfolioRepository;
import waveit.server.repository.MemberRepository;
import waveit.server.web.dto.PortfolioRes;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioService {
    private final MemberRepository memberRepository;
    private final PortfolioRepository portfolioRepository;


    /**
     * 포트폴리오 링크 저장
     * @param userId
     * @param link 저장할 포트폴리오 링크
     */
    public Portfolio savePortfolio(Long userId, String link) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다. id: " + userId));

        Portfolio portfolio = Portfolio.builder()
                .member(member)
                .link(link)
                .build();

        return portfolioRepository.save(portfolio);
    }

    /**
     * 포트폴리오 링크 수정
     * @param id 포트폴리오 id
     * @param userId user id
     * @param link 수정할 포트폴리오 링크
     */
    @Transactional
    public Portfolio updatePortfolio(Long id, Long userId, String link) throws Exception {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다. id: " + userId));

        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new Exception("포트폴리오가 존재하지 않습니다."));

        if (!portfolio.getMember().getId().equals(userId)) {
            throw new Exception("수정 권한이 없습니다.");
        }

        portfolio.setLink(link);

        return portfolioRepository.save(portfolio);

    }

    /**
     * 포트폴리오 링크 삭제
     * @param id 포트폴리오 id
     */
    @Transactional
    public void deletePortfolio(Long id) throws Exception {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new Exception("포트폴리오가 존재하지 않습니다."));

        portfolioRepository.delete(portfolio);
    }

    /**
     * 포트폴리오 목록 조회
     * @param userId user id
     */
    public List<PortfolioRes> getPortfoliosByUserId(Long userId) {
        List<Portfolio> portfolios = portfolioRepository.findByUserId(userId);
        return portfolios.stream()
                .map(PortfolioConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * 포트폴리오 상세 조회
     * @param userId user id
     * @param portfolioId 포트폴리오 id
     */
    public PortfolioRes getPortfolio(Long userId, Long portfolioId) throws Exception {
        Portfolio portfolio = portfolioRepository.findByIdAndUserId(portfolioId, userId)
                .orElseThrow(() -> new Exception("포트폴리오가 존재하지 않습니다."));
        return PortfolioConverter.convertToDTO(portfolio);
    }
}
