package com.million.grpcjavacourse.greeting.client;

import com.proto.greeting.GreetingRequest;
import com.proto.greeting.GreetingResponse;
import com.proto.greeting.GreetingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetingClient {

    public static void main(String[] args) {

        if (args.length == 0) {
            log.info("Need one argument to work");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        switch (args[0]) {

            case "greet":
                doGreet(channel);
                break;

            case "greetManyTimes":
                doGreetManyTimes(channel);
                break;

            default:
                log.info("Keyword Invalid: " + args[0]);
        }

        log.info("Shutting down");
        channel.shutdown();
    }

    private static void doGreet(ManagedChannel channel) {

        log.info("Enter doGreet");

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingResponse response = stub.greet(GreetingRequest.newBuilder().setFirstName("John").build());

        log.info("Greeting: {}", response.getResult());
    }

    private static void doGreetManyTimes(ManagedChannel channel) {

        log.info("Enter doGreetManyTimes");

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        stub.greetManyTimes(GreetingRequest.newBuilder().setFirstName("John").build()).forEachRemaining(greetingResponse -> {
            log.info(greetingResponse.getResult());
        });
    }
}
