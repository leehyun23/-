package com.hyunjoonlee.board.dto;

import com.hyunjoonlee.board.entity.BoardDef;
import lombok.*;

import java.time.LocalDateTime;

/**
 * PostDTO Class
 */
@Data @Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private int postNo;
    private String boardDefCd;
    private String postSj;
    private String postCn;
    private String regstrId;
    private LocalDateTime regstrDt;

}
