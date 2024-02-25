package waveit.server.converter;

import waveit.server.domain.Post;
import waveit.server.web.dto.PostReq;
import waveit.server.web.dto.PostRes;

public class PostConverter {

        public static PostRes convertPostResToDTO(Post post) {

            PostRes postRes = new PostRes();

            postRes.setId(post.getId());
            postRes.setPostId(post.getId());
            postRes.setTitle(post.getTitle());
            postRes.setCategory(post.getCategory());
            postRes.setPart(post.getPart());
            postRes.setCnt(post.getCnt());
            postRes.setDescription(post.getDescription());

            return postRes;
        }

        public static PostReq convertPostReqToDTO(Post post) {

            PostReq postReq = new PostReq();

            postReq.setTitle(postReq.getTitle());
            postReq.setCategory(postReq.getCategory());
            postReq.setPart(postReq.getPart());
            postReq.setDescription(postReq.getDescription());
            postReq.setCnt(postReq.getCnt());

            return postReq;
        }

}



