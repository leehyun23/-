package com.hyunjoonlee.board.controller;


import com.hyunjoonlee.board.dto.PostDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.entity.Post;
import com.hyunjoonlee.board.service.BoardDefService;
import com.hyunjoonlee.board.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class PostControllerTest {

    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PostController boardDefController;

    @Autowired
    private BoardDefService boardDefService;

    @Test
    public void testReadController() throws Exception {
        int postNo = 1;
        PostDto postDto = PostDto.builder()
                .postNo(postNo)
                .boardDefCd("b001")
                .postSj("TestTitle")
                .postCn("TestContent")
                .regstrId("TestWriter")
                .regstrDt(LocalDateTime.now())
                .build();


        when(postService.readOne(postNo)).thenReturn(postDto);

        mockMvc.perform(get("/posts/read/{postNo}", postNo))
                .andExpect(status().isOk())
                .andExpect(view().name("/post/read"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", postDto));
    }

    @Test
    void listTest() throws Exception {
        List<Post> posts = new ArrayList<>();
        posts.add(Post.builder()
                .postNo(1)
                .boardDef(new BoardDef("b001","test" ))
                .postSj("TestTitle1")
                .postCn("TestContent1")
                .regstrId("TestWriter1")
                .build());
        posts.add(Post.builder()
                .postNo(2)
                .boardDef(new BoardDef("b002","test" ))
                .postSj("TestTitle2")
                .postCn("TestContent2")
                .regstrId("TestWriter2")
                .build());

        when(postService.list()).thenReturn(posts);

        mockMvc.perform(get("/posts/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/post/list"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attribute("posts", posts));
    }


    @Test
    void registerTest() throws Exception {


        PostDto postDto = new PostDto();
        postDto.setBoardDefCd("b001");
        postDto.setPostSj("TestTitle");
        postDto.setPostCn("TestContent");

        when(postService.register(postDto)).thenReturn(1);

        mockMvc.perform(post("/posts/register")
                        .param("postSj", "TestTitle")
                        .param("postCn", "TestContent"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/post/list"));
    }
    @Test
    void postModifyTest() throws Exception {
        PostDto postDto = PostDto.builder()
                .postNo(1)
                .boardDefCd("b001")
                .postSj("TestTitleUpdate")
                .postCn("TestContent")
                .regstrId("TestWriter")
                .build();

        when(postService.modify(eq(postDto))).thenReturn(1);

        mockMvc.perform(put("/posts/modify/{postNo}", 1)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("boardDefCd", "b001")
                        .param("postSj", "TestTitleUpdate")
                        .param("postCn", "TestContent")
                        .param("regstrId", "TestWriter"))
                .andExpect(status().is3xxRedirection()) // 주로 리디렉션을 할 때 사용됩니다.
                .andExpect(redirectedUrl("/post/list"));
    }

    @Test
    void postRemoveTest() throws Exception {
        int postNo = 1;
        mockMvc.perform(delete("/posts/remove/{postNo}", postNo))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/post/list"));
    }
}