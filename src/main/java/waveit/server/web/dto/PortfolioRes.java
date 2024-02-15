package waveit.server.web.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioRes {
    private Long id;
    private String link;
}
