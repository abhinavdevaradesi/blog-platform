package com.jpaprojects.blogplatform.service;

import com.jpaprojects.blogplatform.model.Post;
import com.jpaprojects.blogplatform.model.User;
import com.jpaprojects.blogplatform.repository.PostRepository;
import com.jpaprojects.blogplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public Post createPost(Long userId, Post post) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cannot create post. User not found with id: " + userId));

        user.addPost(post);
        return postRepository.save(post);
    }

    @Transactional
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot delete. Post not found with id: " + id));
        if(post.getAuthor() != null) {
            post.getAuthor().removePost(post);
        }
        postRepository.delete(post);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}
