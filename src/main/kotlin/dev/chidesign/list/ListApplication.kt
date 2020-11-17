package dev.chidesign.list

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ListApplication
{

	@Bean
	fun init(repository: UserRepository) = CommandLineRunner {
		repository.save(User("kokoro90@gmail.com", "Daniel", "Weber", true, "asdg", "", 0))
		repository.save(User("jalale@gmail.com", "Jalale", "Weber", true, "asdg", "", 0))
	}
}

fun main(args: Array<String>) {
	runApplication<ListApplication>(*args)
}
