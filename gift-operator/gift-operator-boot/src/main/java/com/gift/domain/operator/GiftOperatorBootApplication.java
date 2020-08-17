package com.gift.domain.operator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * <p>工程启动类</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className GiftoperatorBootApplication
 * @sine 2020/3/24 9:51
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.gift")
@ImportResource(locations={"classpath*:META-INF/springClound/spring.xml"})
@MapperScan("com.gift.domain.**.model.mapper")
public class GiftOperatorBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftOperatorBootApplication.class,args);
    }

}
