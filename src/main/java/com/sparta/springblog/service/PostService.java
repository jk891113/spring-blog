package com.sparta.springblog.service;

import com.sparta.springblog.entity.Comment;
import com.sparta.springblog.entity.Post;
import com.sparta.springblog.entity.User;
import com.sparta.springblog.repository.PostRepository;
import com.sparta.springblog.repository.CategoryRepository;
import com.sparta.springblog.repository.CommentRepository;
import com.sparta.springblog.repository.UserRepository;
import com.sparta.springblog.dto.request.PostRequestDto;
import com.sparta.springblog.dto.request.UpdateRequestDto;
import com.sparta.springblog.dto.request.UsernameRequestDto;
import com.sparta.springblog.dto.response.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDto createPosting(PostRequestDto requestDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        categoryRepository.findByCategoryId(requestDto.getCategoryId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 카테고리입니다.")
        );
        Post post = new Post(requestDto, user);
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        postRepository.save(post);
        return new PostResponseDto(post, commentList);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPostings() {
        List<Post> postList = postRepository.getAllByOrderByModifiedAtDesc();
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        List<PostResponseDto> responseDtoList = postList.stream().map(post -> new PostResponseDto(post, commentList)).collect(Collectors.toList());
        return responseDtoList;
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPostingById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        return new PostResponseDto(post, commentList);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostingByUsername(UsernameRequestDto requestDto) {
        String username = requestDto.getUsername();
        List<Post> postList = postRepository.findByUserUsernameOrderByModifiedAtDesc(username);
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        return postList.stream().map(post -> new PostResponseDto(post, commentList)).collect(Collectors.toList());
    }

    public List<PostResponseDto> getAllPostingsByCategory(Long categoryId) {
        categoryRepository.findByCategoryId(categoryId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 카테고리입니다.")
        );
        List<Post> postList = postRepository.findByCategoryIdOrderByModifiedAtDesc(categoryId);
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        return postList.stream().map(post -> new PostResponseDto(post, commentList)).collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto update(Long postId, UpdateRequestDto requestDto, String username) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        categoryRepository.findByCategoryId(requestDto.getCategoryId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 카테고리입니다.")
        );
        if (!post.isPostingWriter(username)) {
            throw new IllegalArgumentException("본인이 작성한 게시글만 수정할 수 있습니다.");
        }
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        post.update(requestDto);
        return new PostResponseDto(post, commentList);
    }

    public PostResponseDto updateAdmin(Long postId, UpdateRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        categoryRepository.findByCategoryId(requestDto.getCategoryId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 카테고리입니다.")
        );
        List<Comment> commentList = commentRepository.getAllByOrderByModifiedAtDesc();
        post.update(requestDto);
        return new PostResponseDto(post, commentList);
    }

    @Transactional
    public void deletePosting(Long postId, String username) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        if (!post.isPostingWriter(username)) {
            throw new IllegalArgumentException("본인이 작성한 게시글만 삭제할 수 있습니다.");
        }
        postRepository.deleteById(postId);
//        commentRepository.deleteAllByIdIn(postId);
    }

    @Transactional
    public void deletePostingAdmin(Long postId) {
        postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        postRepository.deleteById(postId);
//        commentRepository.deleteByPostId(postId);
//        List<Comment> commentList = commentRepository.findByPostId(postId);
//        List<Long> commentIdList = commentList.stream().map(Comment::getId).collect(Collectors.toList());
    }
}
