package com.hyunjoonlee.board.controller;

import com.hyunjoonlee.board.dto.TagDto;
import com.hyunjoonlee.board.entity.Tag;
import com.hyunjoonlee.board.service.TagService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class TagControllerTest {

    @MockBean
    private TagService tagService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TagController tagController;

    @Test
    public void testReadController() throws Exception {
        int tagNo = 1;
        TagDto tagDto = TagDto.builder()
                .tagNo(tagNo)
                .tag("TestTag")
                .boardCd("b001")
                .build();

        when(tagService.readOne(tagNo)).thenReturn(tagDto);

        mockMvc.perform(get("/tags/read/{tagNo}", tagNo))
                .andExpect(status().isOk())
                .andExpect(view().name("/tag/read"))
                .andExpect(model().attributeExists("tag"))
                .andExpect(model().attribute("tag", tagDto));
    }

    @Test
    void listTest() throws Exception {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.builder()
                .tagNo(1)
                .tag("TestTag1")
                .build());
        tags.add(Tag.builder()
                .tagNo(2)
                .tag("TestTag2")
                .build());

        when(tagService.list()).thenReturn(tags);

        mockMvc.perform(get("/tags/List"))
                .andExpect(status().isOk())
                .andExpect(view().name("/tag/list"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attribute("tags", tags));
    }


    @Test
    void registerTest() throws Exception {
        TagDto tagDto = new TagDto();
        tagDto.setTag("TestTag");

        when(tagService.register(tagDto)).thenReturn(1);

        mockMvc.perform(post("/tags/register")
                        .param("tag", "TestTag"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tag/register"));
    }

    @Test
    void ModifyTest() throws Exception {
        TagDto tagDto = TagDto.builder()
                .tagNo(1)
                .tag("UpdatedTestTag")
                .boardCd("b001")
                .build();

        when(tagService.modify(tagDto)).thenReturn(1);

        mockMvc.perform(put("/tags/modify/{tagNo}", 1)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("tag", "UpdatedTestTag")
                        .param("boardDefCd", "b001"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tag/modify"));
    }

    @Test
    void RemoveTest() throws Exception {
        int tagNo = 1;

        when(tagService.remove(anyInt())).thenReturn(tagNo);

        mockMvc.perform(delete("/tags/remove/{tagNo}", tagNo))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tag/list"));
    }
}
