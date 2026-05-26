package com.jpaprojects.blogplatform.repository;

import com.jpaprojects.blogplatform.model.Post;
import com.jpaprojects.blogplatform.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RelationshipTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testUserAndPostRelationshipCascade() {
        User user = new User("dev_mindset", "dev@gmail.com", "Pro Developer");


        Post post1 = new Post("Post 1 title", "This is a temp content for post 1.");
        Post post2 = new Post("Post 2 title", "This is a temp content for post 2.");

        user.addPost(post1);
        user.addPost(post2);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(postRepository.count()).isEqualTo(2);

        savedUser.removePost(post1);

        userRepository.saveAndFlush(savedUser);

        assertThat(postRepository.count()).isEqualTo(1);
    }
}
