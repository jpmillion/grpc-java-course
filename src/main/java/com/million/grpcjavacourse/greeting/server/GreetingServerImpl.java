package com.million.grpcjavacourse.greeting.server;

import com.proto.greeting.GreetingRequest;
import com.proto.greeting.GreetingResponse;
import com.proto.greeting.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;

public class GreetingServerImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greet(GreetingRequest request, StreamObserver<GreetingResponse> response) {
        response.onNext(GreetingResponse.newBuilder().setResult("Hello " + request.getFirstName()).build());
        response.onCompleted();
    }

    @Override
    public void greetManyTimes(GreetingRequest request, StreamObserver<GreetingResponse> responseStreamObserver) {

        GreetingResponse greetingResponse = GreetingResponse.newBuilder().setResult("Hello " + request.getFirstName()).build();

        for (int i = 0; i < 10; i++) {
            responseStreamObserver.onNext(greetingResponse);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        responseStreamObserver.onCompleted();
    }

}
