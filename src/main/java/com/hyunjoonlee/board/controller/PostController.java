package com.hyunjoonlee.board.controller;

import com.hyunjoonlee.board.dto.PostDto;
import com.hyunjoonlee.board.entity.Post;
import com.hyunjoonlee.board.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Post Controller
 */

@Controller
@RequestMapping("/posts")
@Log4j2
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/register")
    public String createPost(PostDto postDto, Model model) {
        int savedPostNo = postService.register(postDto);
        model.addAttribute("savedPostNo", savedPostNo);
        return "redirect:/post/list";
    }

    @GetMapping("/read/{postNo}")
    public String getPostDetails(@PathVariable int postNo, Model model) {
        PostDto post = postService.readOne(postNo);
        model.addAttribute("post", post);
        return "/post/read";
    }

    @GetMapping("/list")
    public String getAllPosts(Model model) {
        List<Post> posts = postService.list();
        model.addAttribute("posts", posts);
        return "/post/list";
    }

    @PutMapping("/modify/{postNo}")
    public String updatePost(@PathVariable("postNo") int postNo, PostDto postDto, Model model) {
        postDto.setPostNo(postNo);
        int updatedPostTitle = postService.modify(postDto);
        model.addAttribute("updatedPostTitle", updatedPostTitle);
        return "redirect:/post/list";
    }

    @DeleteMapping("/remove/{postNo}")
    public String deletePost(@PathVariable int postNo) {
        try { postService.remove(postNo);
            return "redirect:/post/list";
        } catch (Exception e) {
            return "error-page";
        }
    }
}