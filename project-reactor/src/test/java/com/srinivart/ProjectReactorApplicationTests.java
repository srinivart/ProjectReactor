package com.srinivart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ProjectReactorApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	void case1(){
		Flux<String> flux = Flux.just("srinivart");
		flux.subscribe(System.out::println);
	}

	@Test
	void case2(){
		Flux<String> flux1 = Flux.just("sriniv").map(s -> s + "art");
		flux1.subscribe(System.out::println);
	}


	@Test
	void case3(){
		Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
		seq1.subscribe(System.out::println);
	}


	@Test
	void case4(){
		List<String> iterable = Arrays.asList("foo", "bar", "foobar");
		Flux<String> seq2 = Flux.fromIterable(iterable);
		seq2.subscribe(System.out::println);
	}


	@Test
	void case5(){
		Mono<String> noData = Mono.empty();
		Mono<String> data = Mono.just("foo");
		data.subscribe(System.out::println);
	}

	@Test
	void case6(){
		Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
		numbersFromFiveToSeven.subscribe(System.out::println);
	}

    /*
    	  subscribe();

		  subscribe(Consumer<? super T> consumer);

		  subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer);

		  subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer,
          Runnable completeConsumer);

		  subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer,
          Runnable completeConsumer,
          Consumer<? super Subscription> subscriptionConsumer);

     */

	@Test
	void case7(){
		Flux<Integer> ints = Flux.range(1, 3);
		ints.subscribe();
	}


	@Test
	void case8(){
		Flux<Integer> ints = Flux.range(1,5);
		ints.subscribe(i -> System.out.println(i));
	}

	@Test
	void case9(){
		Flux<Integer> ints = Flux.range(1,5);
		ints.map(i-> i*2).subscribe(System.out::println);
	}


	@Test
	void case10(){
		Flux<Integer> ints = Flux.range(1,5);
		ints.map(i-> {
			if(i<=3) return i;
			throw new RuntimeException("Got to 4");
		}).subscribe(System.out::println);
	}

	@Test
	void case11(){
		Flux<Integer> ints = Flux.range(1,5)
								.map(i-> {
										if (i <= 3) return i;
										throw new RuntimeException("Got to 4");
									    });
		ints.subscribe(System.out::println);
	}

	@Test
	void case12(){
		Flux<Integer> ints = Flux.range(1,5)
				.map(i-> {
					if (i <= 3) return i;
					throw new RuntimeException("Got to 4");
				});
		ints.subscribe(System.out::println,
				error -> System.out.println("Error -> : " + error));
	}

	@Test
	void case13(){
		Flux<Integer> ints = Flux.range(1,5);
		ints.subscribe(i -> System.out.println(i),
				error -> System.out.println("Error -> : "+ error),
				() -> System.out.println("Done")
				);
	}

	@Test
	void case14(){
		Flux<Integer> ints = Flux.range(1,5)
				.map(i -> {
					if(i<=3) return i;
					throw new RuntimeException("Got to 4");
				});
		ints.subscribe(i -> System.out.println(i),
				error -> System.out.println("Error -> : "+ error),
				() -> System.out.println("Done")
		);
	}

	@Test
	void case15(){
		Flux<Integer> ints = Flux.range(1, 4);
		ints.subscribe(i -> System.out.println(i),
				error -> System.err.println("Error " + error),
				() -> System.out.println("Done"),
				sub -> sub.request(10));
	}


	@Test
	void case16(){
		Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
		final Flux<String> flux = Flux
				.range(1, 10)
				.map(i -> 10 + i)
				.subscribeOn(s)
				.map(i -> "value " + i);

		new Thread(() -> flux.subscribe(System.out::println));
	}



	@Test
	void case17(){
		Flux.just(1,2,0)
				.map(i -> "100 / " + i + " = " + (100 / i))
				.onErrorReturn("Divided by Zero: (");
	}






}








// Reference: https://projectreactor.io/docs/core/release/reference/