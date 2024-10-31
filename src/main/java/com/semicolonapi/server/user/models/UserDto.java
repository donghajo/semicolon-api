package com.semicolonapi.server.user.models;

import com.semicolonapi.server.user.domains.Role;
import com.semicolonapi.server.user.domains.User;
import lombok.*;

public class UserDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Domain {
        private String userId;
        private String email;
        private String name;
        private String nickname;
        private String birthday;
        private String phone;
        private String type;
        private String profile;
        private Role role;

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .email(email)
                    .name(name)
                    .nickname(nickname)
                    .birthday(birthday)
                    .profile(profile)
                    .phone(phone)
                    .type(type)
                    .role(role).build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {
        private String userId;
        private String email;
        private Role role;
        private String nickname;
        private String profile;
    }

    @Getter
    @Setter
    public static class Request {
        private String userId;
        private String email;
    }
}
