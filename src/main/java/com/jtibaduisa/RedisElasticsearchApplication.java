package com.jtibaduisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisElasticsearchApplication.class, args);
	}

}
