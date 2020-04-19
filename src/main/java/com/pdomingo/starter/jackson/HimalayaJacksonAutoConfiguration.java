package com.pdomingo.starter.jackson;

import com.fasterxml.jackson.databind.Module;
import com.pdomingo.starter.jackson.module.HimalayaModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.jackson.datatype.money.MoneyModule;

@Configuration
@ConditionalOnClass(Module.class)
public class HimalayaJacksonAutoConfiguration {

    @Bean
    Module himalayaModule() {
        return new HimalayaModule();
    }

    @Bean
    Module moneyModule() {
        return new MoneyModule();
    }
}
