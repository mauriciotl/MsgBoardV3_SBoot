package com.mau.msgboardV3_SBoot.spring.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mau", "com.mau.test"})
public class MsgBoardV3SpringBootApplication {

    public static void main(String[] args) {

        SpringApplication.run(MsgBoardV3SpringBootApplication.class, args);
    }

}
