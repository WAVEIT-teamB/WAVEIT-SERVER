package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waveit.server.domain.Portfolio;
import waveit.server.domain.User;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByUserId(Long userId);

    Optional<Portfolio> findByIdAndUserId(Long portfolioId, Long userId);
}
