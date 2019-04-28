package com.gobii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.gobii.mapper")
@SpringBootApplication
public class GobiiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GobiiApplication.class, args);
    }

}
