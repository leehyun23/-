package com.hyunjoonlee.board.controller;

import com.hyunjoonlee.board.dto.TagDto;
import com.hyunjoonlee.board.entity.Tag;
import com.hyunjoonlee.board.service.TagService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Tag Controller
 */
@Controller
@RequestMapping("/tags")
@Log4j2
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/register")
    public String createTag(TagDto tagDto, Model model) {
        int savedTagNo = tagService.register(tagDto);
        model.addAttribute("savedTagNo", savedTagNo);
        return "redirect:/tag/register";
    }

    @GetMapping("/read/{tagNo}")
    public String getTagDetails(@PathVariable int tagNo, Model model) {
        TagDto tag = tagService.readOne(tagNo);
        model.addAttribute("tag", tag);
        return "/tag/read";
    }

    @GetMapping("/List")
    public String getAllTags(Model model) {
        List<Tag> tags = tagService.list();
        model.addAttribute("tags", tags);
        return "/tag/list";
    }

    @PutMapping("/modify/{tagNo}")
    public String updateTag(@PathVariable("tagNo") int tagNo, TagDto tagDto, Model model) {
        tagDto.setTagNo(tagNo);
        int updatedTagNo = tagService.modify(tagDto);
        model.addAttribute("updatedTagNo", updatedTagNo);
        return "redirect:/tag/modify";
    }

    @DeleteMapping("/remove/{tagNo}")
    public String deleteTag(@PathVariable int tagNo) {
        try {
            tagService.remove(tagNo);
            return "redirect:/tag/list";
        } catch (Exception e) {
            return "error-page";
        }
    }
}