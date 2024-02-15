package waveit.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.PortfolioConverter;
import waveit.server.domain.Portfolio;
import waveit.server.domain.User;
import waveit.server.repository.PortfolioRepository;
import waveit.server.repository.UserRepository;
import waveit.server.web.dto.PortfolioRes;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioService {
    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;


    /**
     * 포트폴리오 링크 저장
     * @param userId
     * @param link 저장할 포트폴리오 링크
     */
    public Portfolio savePortfolio(Long userId, String link) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다. id: " + userId));

        Portfolio portfolio = Portfolio.builder()
                .user(user)
                .link(link)
                .build();

        return portfolioRepository.save(portfolio);
    }

    @Transactional
    public Portfolio updatePortfolio(Long id, Long userId, String link) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다. id: " + userId));

        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new Exception("포트폴리오가 존재하지 않습니다."));

        if (!portfolio.getUser().getId().equals(userId)) {
            throw new Exception("수정 권한이 없습니다.");
        }

        portfolio.setLink(link);

        return portfolioRepository.save(portfolio);

    }

    @Transactional
    public void deletePortfolio(Long id) throws Exception {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new Exception("포트폴리오가 존재하지 않습니다."));

        portfolioRepository.delete(portfolio);
    }

    public List<PortfolioRes> getPortfoliosByUserId(Long userId) {
        List<Portfolio> portfolios = portfolioRepository.findByUserId(userId);
        return portfolios.stream()
                .map(PortfolioConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public PortfolioRes getPortfolio(Long userId, Long portfolioId) throws Exception {
        Portfolio portfolio = portfolioRepository.findByIdAndUserId(portfolioId, userId)
                .orElseThrow(() -> new Exception("포트폴리오가 존재하지 않습니다."));
        return PortfolioConverter.convertToDTO(portfolio);
    }
}
