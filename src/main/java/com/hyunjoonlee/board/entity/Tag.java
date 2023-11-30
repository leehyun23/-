package com.hyunjoonlee.board.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Tag Entity Class
 */
@Entity @Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
public class Tag {

    //태그 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_no")
    private int tagNo;

    //태그
    @Column(name = "tag")
    private String tag;

    //게시판(분류)코드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_cd")
    private BoardDef boardDef;

    public void change(String tag) {
        this.tag = tag;
    }
}