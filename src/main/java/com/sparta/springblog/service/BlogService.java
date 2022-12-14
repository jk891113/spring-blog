package com.sparta.springblog.service;

import com.sparta.springblog.entity.Posting;
import com.sparta.springblog.entity.User;
import com.sparta.springblog.jwt.JwtUtil;
import com.sparta.springblog.repository.BlogRepository;
import com.sparta.springblog.repository.UserRepository;
import com.sparta.springblog.requestdto.PostingRequestDto;
import com.sparta.springblog.requestdto.UpdateRequestDto;
import com.sparta.springblog.responsedto.CreateResponseDto;
import com.sparta.springblog.responsedto.PostingResponseDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
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
    private final JwtUtil jwtUtil;

    public CreateResponseDto createPosting(PostingRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            Posting posting = new Posting(requestDto);
            blogRepository.save(posting);
            CreateResponseDto responseDto = new CreateResponseDto(posting);
            return responseDto;
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<PostingResponseDto> getAllPostings() {
        List<Posting> postingList = blogRepository.getAllByOrderByModifiedAtDesc();
        List<PostingResponseDto> responseDtoList = postingList.stream().map(posting -> new PostingResponseDto(posting)).collect(Collectors.toList());
//        List<PostingResponseDto> responseDtoList = new ArrayList<>();
//        for (Posting posting : postingList) {
//            PostingResponseDto responseDto = new PostingResponseDto(posting);
//            responseDtoList.add(responseDto);
//        }
        return responseDtoList;
    }

    @Transactional(readOnly = true)
    public PostingResponseDto getPostingById(Long id) {
        Posting posting = blogRepository.getPostingById(id);
        return new PostingResponseDto(posting);
    }

    @Transactional(readOnly = true)
    public List<PostingResponseDto> getPostingByWriterName(String writerName) {
        List<Posting> postingList = blogRepository.getPostingByWriterName(writerName);
        List<PostingResponseDto> responseDtoList = postingList.stream().map(posting -> new PostingResponseDto(posting)).collect(Collectors.toList());
//        List<PostingResponseDto> responseDtoList = new ArrayList<>();
//        for (Posting posting : postingList) {
//            PostingResponseDto responseDto = new PostingResponseDto(posting);
//            responseDtoList.add(responseDto);
//        }
        return responseDtoList;
    }

    @Transactional
    public String update(Long id, String password, UpdateRequestDto requestDto) {
        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        if (posting.validPassword(password)) {
            posting.update(requestDto);
            return "수정 완료";
        } return "비밀번호가 일치하지 않습니다.";
//        if (password.equals(posting.getPassword())) {
//            posting.update(requestDto);
//            return "수정 완료";
//        } return "비밀번호가 일치하지 않습니다.";
    }

    @Transactional
    public String deletePosting(Long id, String password) {
        Posting posting = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        if (posting.validPassword(password)) {
            blogRepository.deleteById(id);
            return "삭제 완료";
        } return "비밀번호가 일치하지 않습니다.";
//        if (password.equals(posting.getPassword())) {
//            blogRepository.deleteById(id);
//            return "삭제 완료";
//        } return "비밀번호가 일치하지 않습니다.";
    }
}