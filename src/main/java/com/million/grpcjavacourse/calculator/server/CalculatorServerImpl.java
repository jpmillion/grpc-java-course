package com.million.grpcjavacourse.calculator.server;

import com.proto.calculator.CalculateRequest;
import com.proto.calculator.CalculateResponse;
import com.proto.calculator.CalculatorServiceGrpc;
import io.grpc.stub.StreamObserver;

public class CalculatorServerImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void calculate(CalculateRequest request, StreamObserver<CalculateResponse> response) {
        response.onNext(CalculateResponse.newBuilder().setSum(request.getFirstAddend() + request.getSecondAddend()).build());
        response.onCompleted();
    }

}
