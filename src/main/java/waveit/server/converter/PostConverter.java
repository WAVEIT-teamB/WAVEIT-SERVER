package waveit.server.converter;

import waveit.server.domain.Portfolio;
import waveit.server.domain.Post;
import waveit.server.web.dto.LikesRes;
import waveit.server.web.dto.PortfolioRes;
import waveit.server.web.dto.PostRes;

public class PostConverter {
    public static PostRes convertToDTO(Post post) {
        PostRes postRes = new PostRes();

        postRes.setId(post.getId());
        postRes.setPostId(post.getId());
        postRes.setTitle(post.getTitle());
        postRes.setCategory(post.getCategory());
        postRes.setPart(post.getPart());

        return postRes;
    }
}
