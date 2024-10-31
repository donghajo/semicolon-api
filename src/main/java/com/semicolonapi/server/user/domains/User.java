package com.semicolonapi.server.user.domains;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert // insert 시 null 필드 제외
@DynamicUpdate // update 시 null 필드 제외
@Entity(name = "tb_user")
public class User {
    @Id
//    @Column(name = "uid", length=36, columnDefinition = "varchar DEFAULT uuid_generate_v4()") // uuid-ossp 모듈
    @Column(name = "user_id") // uuid-ossp 모듈
    @Comment("사용자 아이디")
    private String userId;

    @Comment("사용자 이메일")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "profile")
    private String profile;

    @Column(name = "type")
    private String type;

    @Enumerated(EnumType.STRING)
    @Comment("권한")
    @Column
    private Role role;

}
