package com.example.login;



import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.example.login")
@Log4j2
public class LoginApplication {
    public static void main(String[] args) {

        SpringApplication.run(LoginApplication.class, args);
        log.info("=====项目启动中=====");

    }

}
