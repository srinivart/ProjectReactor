package com.srinivart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ProjectReactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectReactorApplication.class, args);


		Flux<String> flux = Flux.just("srinivart");
		flux.subscribe(System.out::println);

		Flux<String> flux1 = Flux.just("sriniv").map(s -> s + "art");
		flux1.subscribe(System.out::println);


	}


}
