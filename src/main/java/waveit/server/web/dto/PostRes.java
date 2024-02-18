package waveit.server.web.dto;

import lombok.*;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRes {
    private Long id;
    private Long postId;
    private String title;
    private Category category;
    private Part part;
    private Long cnt;
}
