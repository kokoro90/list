package dev.chidesign.list

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@EnableCaching
class ListApplication

fun main(args: Array<String>) {
	runApplication<ListApplication>(*args)
}

@Configuration
class LiatAppConfig {
	@Bean
	fun cacheManager(): CacheManager = ConcurrentMapCacheManager("authcache").apply {  }
}