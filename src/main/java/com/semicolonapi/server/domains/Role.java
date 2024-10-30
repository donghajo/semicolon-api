package com.semicolonapi.server.domains;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER"),
    ROLE_ANONYMOUS("ROLE_ANONYMOUS");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
 }
