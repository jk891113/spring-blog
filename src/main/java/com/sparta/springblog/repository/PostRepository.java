package com.sparta.springblog.repository;

import com.sparta.springblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getAllByOrderByModifiedAtDesc();
    List<Post> findByUserUsernameOrderByModifiedAtDesc(String username);
    List<Post> findByCategoryIdOrderByModifiedAtDesc(Long categoryId);
    @Transactional
    @Modifying
    @Query("delete from Post p where p.id = :id")
    void deleteById(@Param("id") Long id);
}
