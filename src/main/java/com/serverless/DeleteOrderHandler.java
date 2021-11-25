package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class DeleteOrderHandler implements RequestHandler<ApiGatewayRequest,ApiGatewayResponse> {
    private orderdao orderdao = new orderdao();
    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> pathParameters = input.getPathParameters();
            String id = pathParameters.get("id");
            ordermodel order = orderdao.findById(id);
            orderdao.delete(order);
            return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(true).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiGatewayResponse.builder().setStatusCode(500).setObjectBody(false).build();
    }
}
