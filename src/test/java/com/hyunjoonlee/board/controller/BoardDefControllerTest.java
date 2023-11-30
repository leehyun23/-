package com.hyunjoonlee.board.controller;

import com.hyunjoonlee.board.dto.BoardDefDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.repository.BoardDefRepository;
import com.hyunjoonlee.board.service.BoardDefService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class BoardDefControllerTest {

    @MockBean
    private BoardDefService boardDefService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private BoardDefController boardDefController;

    @Autowired
    private BoardDefRepository boardDefRepository;

    @Test
    public void testReadController() throws Exception {
        BoardDefDto boardDefDto = new BoardDefDto();
        boardDefDto.setBoardCd("sample_board_cd");
        boardDefDto.setBoardNm("Sample Board");

        when(boardDefService.readOne(anyString())).thenReturn(boardDefDto); // 시나리오

        mockMvc.perform(get("/boardDefs/read").param("boardcd", "sample_board_cd"))
                .andExpect(status().isOk())
                .andExpect(view().name("/board/read"))
                .andExpect(model().attributeExists("boardDef"))
                .andExpect(model().attribute("boardDef", boardDefDto))
        ;
    }

    @Test
    void listTest() throws Exception {

        List<BoardDef> boardDefs = new ArrayList<>();
        boardDefs.add(new BoardDef("1", "Board 1"));
        boardDefs.add(new BoardDef("2", "Board 2"));

        when(boardDefService.list()).thenReturn(boardDefs);

        mockMvc.perform(get("/boardDefs/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/boardDef/list"))
                .andExpect(model().attributeExists("boardDef"))
                .andExpect(model().attribute("boardDef", boardDefs))
        ;
    }

    @Test
    void registerTest() throws Exception {

        BoardDefDto boardDefDto = new BoardDefDto();
        boardDefDto.setBoardCd("testCd"); // 적절한 값으로 설정
        boardDefDto.setBoardNm("tesNm"); // 적절한 값으로 설정

        when(boardDefService.register(boardDefDto)).thenReturn("testCd");

        mockMvc.perform(post("/boardDefs/register")
                        .param("boardCd", "testCd")
                        .param("boardNm", "tesNm"))
                .andExpect(status().is3xxRedirection()) // 주로 리디렉션을 할 때 사용됩니다. 예를 들어, 302 Found나 307 Temporary Redirect 등이 3xx Redirection에 속합니다
                .andExpect(redirectedUrl("/boardDef/list"));
    }


    @Test
    void modifyTest() throws Exception {

        BoardDefDto boardDefDto = new BoardDefDto();
        boardDefDto.setBoardCd("testCd");
        boardDefDto.setBoardNm("tesNm");


        when(boardDefService.modify(eq("testCd"), any(BoardDefDto.class)))
                .thenReturn("tesNm");

        mockMvc.perform(put("/boardDefs/modify/sample_board_cd")
                        .param("boardDefCd", "testCd")
                        .param("boardNm", "tesNm"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/boardDef/list"));
    }

    @Test
    void removeTest() throws Exception {
        String boardCd = "testCd";

        mockMvc.perform(delete("/boardDefs/remove/{boardCd}", boardCd))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/boardDef/list"));
    }

}
