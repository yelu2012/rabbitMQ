package com.uws.yl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.uws.yl.mapper")
public class ReceiveApp {

    public static void main(String[] args) {
        SpringApplication.run(ReceiveApp.class, args);
    }
}
