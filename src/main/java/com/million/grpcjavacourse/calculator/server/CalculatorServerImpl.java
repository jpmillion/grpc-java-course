package com.million.grpcjavacourse.calculator.server;

import com.proto.calculator.*;
import io.grpc.stub.StreamObserver;

public class CalculatorServerImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void calculate(CalculateRequest request, StreamObserver<CalculateResponse> response) {
        response.onNext(CalculateResponse.newBuilder().setSum(request.getFirstAddend() + request.getSecondAddend()).build());
        response.onCompleted();
    }

    @Override
    public void primeNumberDecomposition(PrimesRequest request, StreamObserver<PrimesResponse> responseStreamObserver) {

        int k = 2;
        int n = request.getNumber();

        while (n > 1) {
            while (n % k == 0) {
                responseStreamObserver.onNext(PrimesResponse.newBuilder().setPrimeNumber(k).build());
                n /= k;
            }
            k++;
        }

        responseStreamObserver.onCompleted();
    }

}
