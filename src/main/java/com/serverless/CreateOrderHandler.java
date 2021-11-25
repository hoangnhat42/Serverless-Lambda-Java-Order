package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateOrderHandler implements RequestHandler<ApiGatewayRequest,ApiGatewayResponse> {
    private orderdao orderdao = new orderdao();
    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ordermodel order = mapper.readValue((String) input.getBody(), ordermodel.class);
            orderdao.insert(order);
            return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(order).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ApiGatewayResponse.builder().setStatusCode(500).setObjectBody(input).build();
    }
}
