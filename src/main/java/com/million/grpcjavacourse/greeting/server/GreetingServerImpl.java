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

}
