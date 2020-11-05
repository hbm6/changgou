package com.changgou.goods;

import com.changgou.framework.Entity.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.changgou.*")
@EnableEurekaClient
@MapperScan(basePackages = "com.changgou.goods.dao")
public class GoodsApplication {
    public static void main(String[] args) {

        SpringApplication.run(GoodsApplication.class, args);

    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(0, 0);
    }
}
