package com.jack.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author crazyjack262
 */
@EnableTransactionManagement
@MapperScan("com.jack.admin.mapper")
@SpringBootApplication
public class JackAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(JackAdminApplication.class, args);
    }

}
