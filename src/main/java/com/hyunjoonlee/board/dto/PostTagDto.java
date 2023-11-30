package com.hyunjoonlee.board.dto;

import lombok.*;


/**
 * PostTagDTO Class
 */
@Data
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostTagDto {

    private int postTagId;

    private int postNo;

    private String boardDefCd;

    private int tagNo;

}
