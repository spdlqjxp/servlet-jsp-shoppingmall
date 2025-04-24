package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId){
        //todo#4-1 회원조회
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록
        if (isUserExist(user.getUserId())) {
            throw new UserAlreadyExistsException(user.getUserId());
        }
        int result = userRepository.save(user);
        if (result < 1) {
            throw new UserAlreadyExistsException(user.getUserId());
        }
    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        if (!isUserExist(user.getUserId())) {
            throw new UserNotFoundException("user not found");
        }
        int result = userRepository.update(user);
        if (result < 1) {
            throw new RuntimeException("update failed");
        }
    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        if (!isUserExist(userId)) {
            throw new UserNotFoundException(userId);
        }
        int result = userRepository.deleteByUserId(userId);
        if (result < 1) {
            throw new RuntimeException("update failed");
        }
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
        Optional<User> userOptional = userRepository.findByUserIdAndUserPassword(userId, userPassword);
        if (userOptional.isEmpty()) {
            log.debug("login failed");
            throw new UserNotFoundException(userId);
        }
        userRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());
        return userOptional.get();
    }

    private boolean isUserExist(String userId) {
        int count = userRepository.countByUserId(userId);
        return count > 0;
    }
}
