package com.rarnau.fastquickproto.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.rarnau.fastquickproto.config.SpringContextConfig;

@Configuration
@Import(SpringContextConfig.class)
public class SpringTestConfig {

}
