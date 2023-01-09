package com.sparta.springblog.repository;

import com.sparta.springblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getAllByOrderByModifiedAtDesc();
}
