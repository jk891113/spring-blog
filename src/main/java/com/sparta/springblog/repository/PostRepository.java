package com.sparta.springblog.repository;

import com.sparta.springblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getAllByOrderByModifiedAtDesc();
    List<Post> findByUserUsernameOrderByModifiedAtDesc(String username);
    List<Post> findByCategoryIdOrderByModifiedAtDesc(Long categoryId);
}
