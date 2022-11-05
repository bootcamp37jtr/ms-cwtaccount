package com.nttdata.bootcamp.mscwtaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.nttdata.bootcamp.mscwtaccount.model.ExchangeRate;

@SpringBootApplication
public class MsCwtaccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCwtaccountApplication.class, args);
	}
	
	@Bean
    public ReactiveRedisTemplate<String, ExchangeRate> reactiveJsonPostRedisTemplate(
        ReactiveRedisConnectionFactory connectionFactory) {

        RedisSerializationContext<String, ExchangeRate> serializationContext = RedisSerializationContext
            .<String, ExchangeRate>newSerializationContext(new StringRedisSerializer())
            .hashKey(new StringRedisSerializer())
            .hashValue(new Jackson2JsonRedisSerializer<>(ExchangeRate.class))
            .build();
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }


}
