package com.jpaprojects.blogplatform.controller;

import com.jpaprojects.blogplatform.model.Post;
import com.jpaprojects.blogplatform.model.User;
import com.jpaprojects.blogplatform.service.PostService;
import com.jpaprojects.blogplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class BlogViewController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public BlogViewController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    // 1. GLOBAL FEED: Shows only the blog entries
    @GetMapping("/")
    public String getGlobalFeed(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("newPost", new Post());
        return "feed";
    }

    // 2. DETAILED POST VIEW: Dedicated page for a single article
    @GetMapping("/post/{id}")
    public String getPostDetails(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post != null) {
            model.addAttribute("post", post);
            return "post-detail";
        } else {
            return "redirect:/"; // If the post doesn't exist, send them back to the feed
        }
    }

    // 3. AUTHOR PROFILE VIEW: Shows only posts written by a specific author
    @GetMapping("/author/{id}")
    public String getAuthorProfile(@PathVariable Long id, Model model) {
        return userService.getUserById(id)
                .map(user -> {
                    model.addAttribute("author", user);
                    // Fallback handles both direct list access or service utility methods
                    model.addAttribute("posts", user.getPosts() != null ? user.getPosts() : Collections.emptyList());
                    return "author-profile";
                })
                .orElse("redirect:/");
    }

    @PostMapping("/web/users")
    public String webCreateUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/";
    }

    @PostMapping("/web/posts")
    public String webCreatePost(@RequestParam Long userId, @ModelAttribute Post post) {
        postService.createPost(userId, post);
        return "redirect:/";
    }

    // 4. DELETE POST
    @GetMapping("/web/posts/delete/{id}")
    public String webDeletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id); // Ensure your postService has a deletePost(Long id) method mapping to postRepository.deleteById(id)
        } catch (Exception e) {
            // Safe fallback if post doesn't exist
        }
        return "redirect:/";
    }

    // 5. RENDER EDIT POST PAGE
    @GetMapping("/web/posts/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post != null) {
            model.addAttribute("post", post);
            return "edit-post";
        }
        return "redirect:/";
    }

    // 6. SAVE EDITED POST
    @PostMapping("/web/posts/edit/{id}")
    public String webUpdatePost(@PathVariable Long id, @ModelAttribute Post updatedPost) {
        Post existingPost = postService.getPostById(id);
        if (existingPost != null) {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());
            postService.savePost(existingPost); // Ensure your postService can save/update entities
        }
        return "redirect:/post/" + id;
    }
}