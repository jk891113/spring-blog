package com.sparta.springblog.repository;

import com.sparta.springblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getAllByOrderByModifiedAtDesc();
    List<Comment> getAllByParentIdOrderByModifiedAtDesc(Long parentId);

    @Modifying
    @Query("delete from Comment c where c.id = :id")
    void deleteById(@Param("id") Long id);

    @Modifying
    @Query("delete from Comment c where c.parentId = :parentId")
    void deleteByParentId(@Param("parentId") Long parentId);

    @Modifying
    @Query("delete from Comment c where c.postId = :postId")
    void deleteByPostId(@Param("postId") Long postId);

//    List<Comment> findByPostId(Long postId);
}
