package com.srinivart;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class SampleSubscriber<T> extends BaseSubscriber<T> {

    Logger log = LoggerFactory.getLogger(SampleSubscriber.class);
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    public void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }

    public void hookOnComplete(){
        System.out.println("Successful");
    }

    public void hookOnError(Throwable throwable){
      log.error("hook On Error -> ",throwable);
    }

    public static void main(String args[]){
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1,5);
        ints.subscribe(ss);

        Flux<Integer> int2 = Flux.range(1,10)
                .map(i-> {
                    if(i<5) return i;
                    throw new RuntimeException("Runtime");
                });
        int2.subscribe(ss);


    }
}
