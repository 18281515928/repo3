package com.gift.domain.operator.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @description:
 * @author: yangquan
 * @create: 2020-03-15
 */
@Configuration
public class MessageSourceConfigure {
    @Bean
    @Qualifier("validateMessageSource")
    public MessageSource validateMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.setBasename("classpath:i18n/ValidationMessages");

        return ms;
    }
}