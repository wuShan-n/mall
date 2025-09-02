package com.mall.product;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Product Service Application
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.mall.api.*.feign")
@EnableDubbo
@EnableCaching
@EnableAsync
@EnableTransactionManagement
@MapperScan("com.mall.product.mapper")
@ComponentScan(basePackages = {"com.mall.product", "com.mall.common"})
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
        System.out.println("""
                
                 ██████╗ ██████╗  ██████╗ ██████╗ ██╗   ██╗ ██████╗████████╗
                 ██╔══██╗██╔══██╗██╔═══██╗██╔══██╗██║   ██║██╔════╝╚══██╔══╝
                 ██████╔╝██████╔╝██║   ██║██║  ██║██║   ██║██║        ██║   
                 ██╔═══╝ ██╔══██╗██║   ██║██║  ██║██║   ██║██║        ██║   
                 ██║     ██║  ██║╚██████╔╝██████╔╝╚██████╔╝╚██████╗   ██║   
                 ╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═════╝  ╚═════╝  ╚═════╝   ╚═╝   
                
                 :: Product Service Started Successfully ::
                """);
    }
}