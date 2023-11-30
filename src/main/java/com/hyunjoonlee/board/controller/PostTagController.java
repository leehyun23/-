package com.hyunjoonlee.board.controller;

import com.hyunjoonlee.board.dto.PostTagDto;
import com.hyunjoonlee.board.entity.PostTag;
import com.hyunjoonlee.board.service.PostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PostTag Controller
 */
@Controller
@RequestMapping("/postTags")
public class PostTagController {

    @Autowired
    private PostTagService postTagService;

    @PostMapping("/register")
    public String registerPostTag(PostTagDto postTagDto, Model model) {
        int savedPostTagId = postTagService.register(postTagDto);
        model.addAttribute("savedPostTagId", savedPostTagId);
        return "redirect:/postTag/register";
    }

    @GetMapping("/list")
    public String listPostTags(Model model) {
        List<PostTag> postTags = postTagService.list();
        model.addAttribute("postTags", postTags);
        return "/postTag/list";
    }

    @GetMapping("/read/{postTagId}")
    public String readOne(@PathVariable("postTagId") int postTagId, Model model) {
        PostTagDto postTagDto = postTagService.readOne(postTagId);
        model.addAttribute("postTag", postTagDto);
        return "/postTag/read";
    }

    @DeleteMapping("/remove/{postTagId}")
    public String removePostTag(@PathVariable int postTagId) {
        try {
            postTagService.remove(postTagId);
            return "redirect:/postTag/list";
        } catch (Exception e) {
            return "error-page";
        }
    }

}