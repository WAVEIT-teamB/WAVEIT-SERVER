package waveit.server.converter;

import waveit.server.domain.Likes;
import waveit.server.web.dto.LikesRes;

public class LikesConverter {
    public static LikesRes convertToDTO(Likes likes) {
        LikesRes likesRes = new LikesRes();

        likesRes.setId(likes.getId());
        likesRes.setPostId(likes.getPost().getId());
        likesRes.setTitle(likes.getPost().getTitle());
        likesRes.setCategory(likes.getPost().getCategory());
        likesRes.setPart(likes.getPost().getPart());

        return likesRes;
    }
}
