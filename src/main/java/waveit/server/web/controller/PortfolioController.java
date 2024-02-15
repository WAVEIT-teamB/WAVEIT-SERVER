package waveit.server.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waveit.server.domain.Portfolio;
import waveit.server.global.payload.ApiResponse;
import waveit.server.service.PortfolioService;
import waveit.server.web.dto.PortfolioReq;
import waveit.server.web.dto.PortfolioRes;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping("/portfolio")
    public ResponseEntity<?> savePortfolio(@RequestBody PortfolioReq portfolioReq) {
        try {
            Portfolio portfolio = portfolioService.savePortfolio(portfolioReq.getUserId(), portfolioReq.getLink());

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information("포트폴리오 링크 : " + portfolio.getLink() + "가 저장되었습니다.")
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/portfolio/{id}")
    public ResponseEntity<?> updatePortfolio(@PathVariable Long id,@RequestBody PortfolioReq portfolioReq) {
        try {
            Portfolio portfolio = portfolioService.updatePortfolio(id, portfolioReq.getUserId(), portfolioReq.getLink());

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information("포트폴리오 링크 : " + portfolio.getLink() + "가 수정되었습니다.")
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/portfolio/{id}")
    public ResponseEntity<?> deletePortfolio(@PathVariable Long id) {
        try {
            portfolioService.deletePortfolio(id);

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information("포트폴리오가 삭제되었습니다.")
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/portfolio/user/{userId}")
    public ResponseEntity<?> getPortfoliosByUserId(@PathVariable Long userId) {
        try {
            List<PortfolioRes> portfolios = portfolioService.getPortfoliosByUserId(userId);

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(portfolios)
                    .build();

            return ResponseEntity.ok(apiResponse);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/portfolio/user/{userId}/{portfolioId}")
    public ResponseEntity<?> getPortfolio(@PathVariable Long userId, @PathVariable Long portfolioId) {
        try {
            PortfolioRes portfolioRes = portfolioService.getPortfolio(userId, portfolioId);

            ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(portfolioRes)
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
