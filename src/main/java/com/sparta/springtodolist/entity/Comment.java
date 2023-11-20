package com.sparta.springtodolist.entity;

import com.sparta.springtodolist.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "comment") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
    @Column(name = "detail", nullable = false, length = 1000)
    private String detail;

    public Comment(CommentRequestDto commentRequestDto,User user,Board board){
        this.user = user;
        this.board =board;
        this.detail = commentRequestDto.getDetail();
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.detail = commentRequestDto.getDetail();
    }
}
