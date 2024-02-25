package waveit.server.web.dto;

import lombok.*;
import waveit.server.domain.enums.Category;
import waveit.server.domain.enums.Part;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostReq {
    private String title;
    private Category category;
    private Part part;
    private String cnt;
    private String description;
}
