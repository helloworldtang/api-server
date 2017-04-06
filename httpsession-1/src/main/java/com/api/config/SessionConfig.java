package com.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by tang.cheng on 2017/3/18.
 * redis中设置的session默认值：
 * 1) "spring:session:sessions:expires:467540de-6b1a-4848-be25-e07c5960c7d3"
 * 2) "spring:session:sessions:467540de-6b1a-4848-be25-e07c5960c7d3"
 * 3) "spring:session:expirations:1489831320000"  //过期时间
 * 服务重启后，redis中保存的session信息没有发生变化
 */
@EnableRedisHttpSession
public class SessionConfig implements BeanClassLoaderAware {
    private ClassLoader classLoader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisSerializer<Object> redisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(SecurityJackson2Modules.getModules(this.classLoader));
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

}
