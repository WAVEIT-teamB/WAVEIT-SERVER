package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waveit.server.domain.Portfolio;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByMemberId(Long userId);

    Optional<Portfolio> findByIdAndMemberId(Long portfolioId, Long userId);
}
