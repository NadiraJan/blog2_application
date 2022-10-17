package nadira_simpleblog.blog2_application.repositories;

import nadira_simpleblog.blog2_application.data.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
