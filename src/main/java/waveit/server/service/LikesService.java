package waveit.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waveit.server.converter.LikesConverter;
import waveit.server.domain.Likes;
import waveit.server.repository.LikesRepository;
import waveit.server.web.dto.LikesRes;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikesService {
    private final LikesRepository likesRepository;

    public List<LikesRes> findLikedPostsByMemberId(Long memberId) {
        List<Likes> likes = likesRepository.findByMemberId(memberId);
        return likes.stream()
                .map(LikesConverter::convertToDTO)
                .collect(Collectors.toList());
    }

}
