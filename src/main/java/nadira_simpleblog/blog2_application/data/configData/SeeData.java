package nadira_simpleblog.blog2_application.data.configData;

import nadira_simpleblog.blog2_application.data.Account;
import nadira_simpleblog.blog2_application.data.Authority;
import nadira_simpleblog.blog2_application.data.Post;

import nadira_simpleblog.blog2_application.repositories.AuthorityRepository;
import nadira_simpleblog.blog2_application.services.AccountService;
import nadira_simpleblog.blog2_application.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeeData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if (posts.size() == 0) {

            Authority admin=new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("user1");
            account1.setLastName("user1user");
            account1.setEmail("user1@mail.com");
            account1.setPassword("password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            account2.setFirstName("admin");
            account2.setLastName("ADMIN");
            account2.setEmail("user2@mail.com");
            account2.setPassword("password");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);

            account2.setAuthorities(authorities2);

            accountService.save(account1);
            accountService.save(account2);

            Post post1 = new Post();
            post1.setTitle("Title of post1");
            post1.setBody("This is a body of post1");
            post1.setAccount(account1);
            postService.save(post1);

            Post post2 = new Post();
            post2.setTitle("Title of post2");
            post2.setBody("This is a body of post2");
            post2.setAccount(account2);

            postService.save(post2);

        }

    }
}
