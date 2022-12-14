package com.sparta.springblog.service;

import com.sparta.springblog.entity.User;
import com.sparta.springblog.repository.UserRepository;
import com.sparta.springblog.requestdto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }

        User user = new User(username, password);
        userRepository.save(user);
    }
}
