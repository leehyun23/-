package com.hyunjoonlee.board.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Post Entity Class
 */

@Entity @Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {

    //글번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_no")
    private int postNo;

    //게시판(분류)코드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_cd")
    private BoardDef boardDef;

    //제목
    @Column(name = "post_sj")
    private String postSj;

    //내용
    @Column(name = "post_cn")
    private String postCn;

    //작성자ID
    @Column(name = "regstr_id")
    private String regstrId;

    //작성일시
    @Column(name = "REG_DT")
    private LocalDateTime regstrDt;


    public  void change(String postSj, String postCn, String regstrId) {
        this.postSj = postSj;
        this.postCn = postCn;
        this.regstrId = regstrId;
    }

}