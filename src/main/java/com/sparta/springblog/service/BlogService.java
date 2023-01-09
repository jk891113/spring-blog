package com.sparta.springblog.service;

import com.sparta.springblog.entity.Comment;
import com.sparta.springblog.entity.Posting;
import com.sparta.springblog.entity.User;
import com.sparta.springblog.repository.BlogRepository;
import com.sparta.springblog.repository.CommentRepository;
import com.sparta.springblog.repository.UserRepository;
import com.sparta.springblog.requestdto.PostingRequestDto;
import com.sparta.springblog.requestdto.UpdateRequestDto;
import com.sparta.springblog.requestdto.UsernameRequestDto;
import com.sparta.springblog.responsedto.PostingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public PostingResponseDto createPosting(PostingRequestDto requestDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Posting posting = new Posting(requestDto, user);
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        blogRepository.save(posting);
        return new PostingResponseDto(posting, commentList);
    }

    @Transactional(readOnly = true)
    public List<PostingResponseDto> getAllPostings() {
        List<Posting> postingList = blogRepository.getAllByOrderByModifiedAtDesc();
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        List<PostingResponseDto> responseDtoList = postingList.stream().map(posting -> new PostingResponseDto(posting, commentList)).collect(Collectors.toList());
        return responseDtoList;
    }

    @Transactional(readOnly = true)
    public PostingResponseDto getPostingById(Long id) {
        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        return new PostingResponseDto(posting, commentList);
    }

    @Transactional(readOnly = true)
    public List<PostingResponseDto> getPostingByUsername(UsernameRequestDto requestDto) {
        String username = requestDto.getUsername();
        List<Posting> postingList = blogRepository.findByUserUsername(username);
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        return postingList.stream().map(posting -> new PostingResponseDto(posting, commentList)).collect(Collectors.toList());
    }

    @Transactional
    public PostingResponseDto update(Long id, UpdateRequestDto requestDto, String username) {
        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        if (!posting.isPostingWriter(username)) {
            throw new IllegalArgumentException("본인이 작성한 게시글만 수정할 수 있습니다.");
        }
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        posting.update(requestDto);
        return new PostingResponseDto(posting, commentList);
    }

    public PostingResponseDto updateAdmin(Long id, UpdateRequestDto requestDto) {
        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        posting.update(requestDto);
        return new PostingResponseDto(posting, commentList);
    }

    @Transactional
    public void deletePosting(Long id, String username) {
        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        if (!posting.isPostingWriter(username)) {
            throw new IllegalArgumentException("본인이 작성한 게시글만 삭제할 수 있습니다.");
        }
        blogRepository.deleteById(id);
    }

    public void deletePostingAdmin(Long id) {
        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        blogRepository.deleteById(id);
    }
}
