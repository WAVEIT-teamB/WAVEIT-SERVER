package waveit.server.converter;

import waveit.server.domain.Portfolio;
import waveit.server.web.dto.PortfolioRes;

public class PortfolioConverter {
    public static PortfolioRes convertToDTO(Portfolio portfolio) {
        PortfolioRes portfolioRes = new PortfolioRes();
        portfolioRes.setId(portfolio.getId());
        portfolioRes.setLink(portfolio.getLink());

        return portfolioRes;
    }
}
