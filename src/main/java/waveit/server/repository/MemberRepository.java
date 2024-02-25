package waveit.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waveit.server.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
