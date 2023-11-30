package com.hyunjoonlee.board.dto;

import lombok.*;

/**
 * TagDTO Class
 */
@Data @Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {

    private int tagNo;

    private String tag;

    private String boardCd;

}
