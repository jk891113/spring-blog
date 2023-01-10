package com.sparta.springblog.repository;

import com.sparta.springblog.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Posting, Long> {
    List<Posting> getAllByOrderByModifiedAtDesc();
    List<Posting> findByUserUsernameOrderByModifiedAtDesc(String username);
    List<Posting> findByCategoryIdOrderByModifiedAtDesc(Long categoryId);
}
