package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetOrderHandler implements RequestHandler<ApiGatewayRequest,ApiGatewayResponse> {
    private orderdao orderdao = new orderdao();
    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
        Map<String, String> origin = new HashMap<>();
        origin.put("Access-Control-Allow-Origin", "*");
        List<ordermodel> ordermodels = orderdao.findAll();

        return ApiGatewayResponse.builder().setHeaders(origin).setStatusCode(200).setObjectBody(ordermodels).build();
    }
}
