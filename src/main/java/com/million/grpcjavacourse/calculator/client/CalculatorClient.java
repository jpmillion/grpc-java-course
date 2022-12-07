package com.million.grpcjavacourse.calculator.client;

import com.proto.calculator.CalculateRequest;
import com.proto.calculator.CalculateResponse;
import com.proto.calculator.CalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class CalculatorClient {

    public static void main(String[] args) {

        if (args.length == 0) {
            log.info("Need one argument to work");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        switch (args[0]) {

            case "calculate":
                doCalculate(channel);
                break;

            default:
                log.info("Keyword Invalid: " + args[0]);
        }

        log.info("Shutting down");
        channel.shutdown();
    }

    private static void doCalculate(ManagedChannel channel) {

        log.info("Enter doCalculate");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter addends");
        int firstAddend = scanner.nextInt();
        int secondAddend = scanner.nextInt();

        CalculateRequest calculateRequest = CalculateRequest.newBuilder().setFirstAddend(firstAddend).setSecondAddend(secondAddend).build();
        CalculatorServiceGrpc.CalculatorServiceBlockingStub blockingStub = CalculatorServiceGrpc.newBlockingStub(channel);

        CalculateResponse response = blockingStub.calculate(calculateRequest);

        log.info("Calculate: {}", response.getSum());
    }
}
