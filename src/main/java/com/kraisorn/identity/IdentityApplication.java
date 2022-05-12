package com.kraisorn.identity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IdentityApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(IdentityApplication.class);

	@Bean
	public CommandLineRunner demo(PersonRepository repository) {
		return (args) -> {
			// save a few people
			repository.save(new Person("Jack", "Bauer","Hello, Kraisorn!"));
			repository.save(new Person("Chloe", "O'Brian","Hello, Kraisorn!"));
			repository.save(new Person("Kim", "Bauer","Hello, Kraisorn!"));
			repository.save(new Person("David", "Palmer","Hello, Kraisorn!"));
			repository.save(new Person("Michelle", "Dessler","Hello, Kraisorn!"));

			// fetch all people
			log.info("People found with findAll():");
			log.info("-------------------------------");
			for (Person person : repository.findAll()) {
				log.info(person.toString());
			}
			log.info("");

			// fetch an individual person by ID
			Person person = repository.findById(1L);
			log.info("People found with findById(1L):");
			log.info("--------------------------------");
			log.info(person.toString());
			log.info("");

			// fetch people by last name
			log.info("People found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Person bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
