package com.korit.servlet_study.dto;

import com.korit.servlet_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    private String username;
    private String password;
    private String name;
    private String email;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(BCrypt.hashpw(password, BCrypt.gensalt(10))) // 비밀번호 암호화
                .name(name)             // (숫자가 클수록 암호화의 복잡도가 올라간다. 그러나 너무 숫자가 크면 암호화 숫자가 늘어난다. 기본값 10정도가 적당)
                .email(email)
                .build();
    }
}
