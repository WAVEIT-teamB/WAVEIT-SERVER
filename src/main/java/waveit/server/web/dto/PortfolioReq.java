package waveit.server.web.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioReq {
    private Long userId;
    private String link;
}