package com.sparta.springblog.service;

import com.sparta.springblog.dto.PostingRequestDto;
import com.sparta.springblog.dto.CreateResponseDto;
import com.sparta.springblog.entity.Posting;
import com.sparta.springblog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public CreateResponseDto createPosting(String writerName, String password, String title, String contents) {
        Posting posting = new Posting(writerName, password, title, contents);
        blogRepository.save(posting);
        CreateResponseDto responseDto = new CreateResponseDto();
        responseDto.getPostingForCreate(posting);
        return responseDto;
    }

    @Transactional(readOnly = true)
    public List<Posting> getAllPostings() {
        return blogRepository.getAllByOrderByModifiedAtDesc();
    }

    @Transactional(readOnly = true)
    public List<Posting> getPostingById(Long id) {
        return blogRepository.getPostingById(id);
    }

    @Transactional(readOnly = true)
    public List<Posting> getPostingByWriterName(String writerName) {
        return blogRepository.findAllByWriterNameOrderByModifiedAtDesc(writerName);
    }

    @Transactional
    public String update(Long id, String password, PostingRequestDto requestDto) {
        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        if (password.equals(posting.getPassword())) {
            posting.update(requestDto);
            return "수정 완료";
        } return "비밀번호가 일치하지 않습니다.";
    }

    @Transactional
    public String deletePosting(Long id, String password) {
        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        if (password.equals(posting.getPassword())) {
            blogRepository.deleteById(id);
            return "삭제 완료";
        } return "비밀번호가 일치하지 않습니다.";
    }
}