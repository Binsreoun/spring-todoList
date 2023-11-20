package com.sparta.springtodolist.repository;

import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard(Board board);
}
