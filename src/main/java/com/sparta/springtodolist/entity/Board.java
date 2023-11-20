package com.sparta.springtodolist.entity;


import com.sparta.springtodolist.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "board") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "detail", nullable = false, length = 1000)
    private String detail;
    @Column(name = "finish")
    private boolean finish;


    public Board(BoardRequestDto boardRequestDto,User user) {
        this.user = user;
        this.title = boardRequestDto.getTitle();
        this.detail = boardRequestDto.getDetail();
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.detail = boardRequestDto.getDetail();
    }

    public void finish(boolean finish) {
        this.finish = finish;
    }

}
