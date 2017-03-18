package com.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by tang.cheng on 2017/3/18.
 * redis中设置的session默认值：
 * 1) "spring:session:sessions:expires:467540de-6b1a-4848-be25-e07c5960c7d3"
 * 2) "spring:session:sessions:467540de-6b1a-4848-be25-e07c5960c7d3"
 * 3) "spring:session:expirations:1489831320000"
 * 服务重启后，redis中保存的session信息没有发生变化
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
}
