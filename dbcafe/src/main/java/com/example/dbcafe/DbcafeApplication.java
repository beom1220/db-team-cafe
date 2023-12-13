package com.example.dbcafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // createdAt 어노테이션 안돼서 설정
public class DbcafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbcafeApplication.class, args);
    }

}
