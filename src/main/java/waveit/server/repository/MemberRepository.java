package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waveit.server.domain.Member;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByLoginId(String loginId);
    Member findByLoginIdAndPassword(String loginId, String password);
}
