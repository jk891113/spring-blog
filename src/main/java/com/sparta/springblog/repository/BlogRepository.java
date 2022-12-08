package com.sparta.springblog.repository;

import com.sparta.springblog.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Posting, Long> {
    List<Posting> getAllByOrderByModifiedAtDesc();
    List<Posting> getPostingByWriterName(String writerName);
    List<Posting> findAllByWriterNameOrderByModifiedAtDesc(String writerName);
    List<Posting> getPostingById(Long id);
}
