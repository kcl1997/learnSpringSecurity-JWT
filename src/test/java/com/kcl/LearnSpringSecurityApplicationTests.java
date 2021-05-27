package com.kcl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class LearnSpringSecurityApplicationTests {

    @Test
    void contextLoads() {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");

        System.out.println(encode); //$2a$10$svp.cYEelXIMGz/t952otuk2I7COcQLh.ZY3mC4TItFMtaC1Ij17m

        boolean ismatch = passwordEncoder.matches("123456", "$2a$10$svp.cYEelXIMGz/t952otuk2I7COcQLh.ZY3mC4TItFMtaC1Ij17m");
        System.out.println(ismatch);

    }

}
