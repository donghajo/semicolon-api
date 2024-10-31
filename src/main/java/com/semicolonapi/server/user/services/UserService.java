package com.semicolonapi.server.user.services;

import com.semicolonapi.server.user.domains.User;
import com.semicolonapi.server.user.models.UserDto;
import com.semicolonapi.server.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void create(UserDto.Domain user) {
        User entity = user.toEntity();
        userRepository.save(entity);
    }

    public List<UserDto.Info> findAll(){
        List<User> entities = userRepository.findAll();

        List<UserDto.Info> users = new ArrayList<>();
        for(User entity : entities) {
            UserDto.Info user = UserDto.Info.builder()
                    .userId(entity.getUserId())
                    .email(entity.getEmail())
                    .role(entity.getRole())
                    .nickname(entity.getNickname())
                    .build();
            users.add(user);
        }
        return users;
    }

    public UserDto.Info findByUserId(String userId){
        User entity = userRepository.findByUserId(userId);
        if(entity != null) {
            UserDto.Info user = UserDto.Info.builder()
                    .userId(entity.getUserId())
                    .email(entity.getEmail())
                    .role(entity.getRole())
                    .nickname(entity.getNickname())
                    .build();

            return user;
        }
        return null;
    }

}
