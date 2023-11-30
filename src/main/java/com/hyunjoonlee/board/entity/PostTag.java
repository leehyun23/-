package com.hyunjoonlee.board.entity;


import lombok.*;

import javax.persistence.*;

/**
 * PostTag Entity Class
 */
//주신 자료에서 태크 ID 가 서로 다르게(TAG_NO, TAG_ID) 명시 되어 있어서 TAG_NO로 통일 해 사용했습니다.

@Entity @Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post_tag")
public class PostTag {

    //게시물 태그 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postTagId;

    //글번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_no")
    private Post post;

    //게시판(분류)코드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_cd")
    private BoardDef boardDef;

    //태그ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_no")
    private Tag tag;

}