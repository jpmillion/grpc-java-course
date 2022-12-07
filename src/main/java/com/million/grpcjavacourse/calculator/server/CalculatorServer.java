package com.million.grpcjavacourse.calculator.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CalculatorServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50052;

        Server server = ServerBuilder
                .forPort(port)
                .addService(new CalculatorServerImpl())
                .build();

        server.start();
        log.info("Server started, listening on port: {}", port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Received Shutdown request");
            server.shutdown();
            log.info("Server stopped");
        }));

        server.awaitTermination();
    }
}
