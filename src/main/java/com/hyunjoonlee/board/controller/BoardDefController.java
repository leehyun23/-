package com.hyunjoonlee.board.controller;


import com.hyunjoonlee.board.dto.BoardDefDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.service.BoardDefService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BoardDef Controller
 */
@Controller
@RequestMapping("/boardDefs")
@Log4j2
public class BoardDefController {
    @Autowired
    private BoardDefService boardDefService;

    @GetMapping("/read")
    public String read(String boardcd, Model model) {
        BoardDefDto boardDefDto = boardDefService.readOne(boardcd);
        model.addAttribute("boardDef", boardDefDto);
        return "/board/read";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDef> boardDefs = boardDefService.list();
        model.addAttribute("boardDef", boardDefs);
        return "/boardDef/list";
    }

    @PostMapping("/register")
    public String register(BoardDefDto boardDefDto, Model model) {
        String boardCd = boardDefService.register(boardDefDto);
        model.addAttribute("boardDef", boardCd);
        return "redirect:/boardDef/list";
    }


    @PutMapping("/modify/{boardDefCd}")
    public String modify(@PathVariable("boardDefCd") String boardDefCd,
                         BoardDefDto boardDefDto, Model model) {
        String modifiedBoardDefNm = boardDefService.modify(boardDefCd, boardDefDto);
        model.addAttribute("boardDef", modifiedBoardDefNm);
        return "redirect:/boardDef/list";
    }

    @DeleteMapping("/remove/{boardDefCd}")
    public String remove(@PathVariable("boardDefCd") String boardDefCd) {
        try {
            boardDefService.remove(boardDefCd);
            return "redirect:/boardDef/list";
        } catch (Exception e) {
            return "error-page";
        }
    }

}
