package com.hyunjoonlee.board.dto;

import lombok.*;

/**
 * BoardDefDTO Class
 */
@Data @Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDefDto {

    private String boardCd;

    private String boardNm;

}
