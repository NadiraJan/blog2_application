package nadira_simpleblog.blog2_application.repositories;

import nadira_simpleblog.blog2_application.data.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
