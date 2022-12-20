package com.sparta.springblog.service;

import com.sparta.springblog.entity.Posting;
import com.sparta.springblog.entity.User;
import com.sparta.springblog.repository.BlogRepository;
import com.sparta.springblog.repository.UserRepository;
import com.sparta.springblog.requestdto.PostingRequestDto;
import com.sparta.springblog.requestdto.UpdateRequestDto;
import com.sparta.springblog.responsedto.PostingResponseDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public PostingResponseDto createPosting(PostingRequestDto requestDto, Claims claims) {
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        Posting posting = new Posting(requestDto, user);
        blogRepository.save(posting);
        return new PostingResponseDto(posting);
    }

    @Transactional(readOnly = true)
    public List<PostingResponseDto> getAllPostings() {
        List<Posting> postingList = blogRepository.getAllByOrderByModifiedAtDesc();
        List<PostingResponseDto> responseDtoList = postingList.stream().map(posting -> new PostingResponseDto(posting)).collect(Collectors.toList());

        return responseDtoList;
    }

    @Transactional(readOnly = true)
    public PostingResponseDto getPostingById(Long id) {
        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        return new PostingResponseDto(posting);
    }

    @Transactional(readOnly = true)
    public List<PostingResponseDto> getPostingByUsername(String username) {
        List<Posting> postingList = blogRepository.findByUserUsername(username);
        return postingList.stream().map(posting -> new PostingResponseDto(posting)).collect(Collectors.toList());
    }

    @Transactional
    public PostingResponseDto update(Long id, UpdateRequestDto requestDto, Claims claims) {
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );

        if (user.isPostingWriter(posting)) {
            throw new IllegalArgumentException("본인이 작성한 게시글만 수정할 수 있습니다.");
        }
        posting.update(requestDto);
        return new PostingResponseDto(posting);
    }

    @Transactional
    public void deletePosting(Long id, Claims claims) {
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );

        if (user.isPostingWriter(posting)) {
            throw new IllegalArgumentException("본인이 작성한 게시글만 삭제할 수 있습니다.");
        }

        blogRepository.deleteById(id);
    }
}
