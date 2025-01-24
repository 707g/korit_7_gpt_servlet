package com.korit.servlet_study.security.jwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // method 위에
@Retention(RetentionPolicy.RUNTIME) // 적용 시점
public @interface JwtValid {
}
