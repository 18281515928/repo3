package com.gift.domain.operator.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: MybatisPlus分页插件配置
 * @author: yangquan
 * @create: 2020-03-16
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //最大条数
        paginationInterceptor.setLimit(100);
        return paginationInterceptor;
    }
}