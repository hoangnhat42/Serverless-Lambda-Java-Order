package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetOrderHandler implements RequestHandler<ApiGatewayRequest,ApiGatewayResponse> {
    private orderdao orderdao = new orderdao();
    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
        List<ordermodel> ordermodels = orderdao.findAll();

        return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(ordermodels).build();
    }
}
