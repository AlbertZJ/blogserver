package org.sang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by albert on 2019/12/19.
 */
@SpringBootApplication
@EnableScheduling  // 开启定时任务支持
public class BlogserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogserverApplication.class, args);
    }
}
