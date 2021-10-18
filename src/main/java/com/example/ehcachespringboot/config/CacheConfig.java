package com.example.ehcachespringboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;


@Component
public class CacheConfig implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);

	private final CacheManager cacheManager;

	public CacheConfig(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void run(String... args) throws Exception { // 빌드 실행
		log.info("\n\n" + "=========================================================\n"
				+ "Using cache manager: " + this.cacheManager.getClass().getName() + "\n"
				+ "=========================================================\n\n");
	}
}
