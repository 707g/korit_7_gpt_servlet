package com.korit.servlet_study.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int userId;
    private String username;
    @JsonIgnore // JSON으로 변환할 때 password 제외
    private String password;
    private String name;
    private String email;
}
