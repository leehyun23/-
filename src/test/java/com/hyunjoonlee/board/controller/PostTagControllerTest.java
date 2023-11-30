package com.hyunjoonlee.board.controller;


import com.hyunjoonlee.board.dto.PostTagDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.entity.PostTag;
import com.hyunjoonlee.board.service.PostTagService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostTagController.class)
public class PostTagControllerTest {

    @MockBean
    private PostTagService postTagService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PostTagController postTagController;

    @Test
    public void readOneTest() throws Exception {
        int postTagId = 1;
        PostTagDto postTagDto = PostTagDto.builder()
                .postTagId(postTagId)
                .postNo(1)
                .boardDefCd("b001")
                .tagNo(1)
                .build();

        when(postTagService.readOne(postTagId)).thenReturn(postTagDto);

        mockMvc.perform(get("/postTags/read/{postTagId}", postTagId))
                .andExpect(status().isOk())
                .andExpect(view().name("/postTag/read"))
                .andExpect(model().attributeExists("postTag"))
                .andExpect(model().attribute("postTag", postTagDto));
    }

    @Test
    void listTest() throws Exception {
        List<PostTag> postTags = new ArrayList<>();
        postTags.add((PostTag.builder()
                .postTagId(1)
                .boardDef(new BoardDef("b001","test" ))
                .build()));
        when(postTagService.list()).thenReturn(postTags);

        mockMvc.perform(get("/postTags/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/postTag/list"))
                .andExpect(model().attributeExists("postTags"))
                .andExpect(model().attribute("postTags", postTags));
    }

    @Test
    void registerTest() throws Exception {

        PostTagDto postTagDto = new PostTagDto();
        postTagDto.setPostNo(1);
        postTagDto.setBoardDefCd("b001");
        postTagDto.setTagNo(1);

        when(postTagService.register(postTagDto)).thenReturn(1);
        mockMvc.perform(post("/postTags/register")
                        .param("postNo", "1")
                        .param("boardDefCd", "b001")
                        .param("tagNo", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/postTag/register"));
    }

    @Test
    void removeTest() throws Exception {
        int postTagId = 1;

        when(postTagService.remove(anyInt())).thenReturn(postTagId);

        mockMvc.perform(delete("/postTags/remove/{postTagId}", postTagId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/postTag/list"));
    }
}