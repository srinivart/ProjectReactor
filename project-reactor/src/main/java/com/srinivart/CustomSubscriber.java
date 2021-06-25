package com.srinivart;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;


public class CustomSubscriber {
    public static void main(String args[]){

        Flux.range(1,10)
                .doOnRequest(r -> System.out.println("request of "+r))
                .subscribe(new BaseSubscriber<Integer>(){

                    public void hookOnSubscribe(Subscription subscription){
                        System.out.println("Subscribed");
                        request(1);
                    }

                    public void hookOnNext(Integer integer){
                        System.out.println("Cancelling after received "+ integer);
                        cancel();
                    }
        });
    }


}
