package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class UpdateOrderHandler implements RequestHandler<ApiGatewayRequest,ApiGatewayResponse> {
    private orderdao orderdao = new orderdao();
    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> origin = new HashMap<>();
        origin.put("Access-Control-Allow-Origin", "*");
        try {
            ordermodel order = mapper.readValue(input.getBody(), ordermodel.class);

            orderdao.update(order, order.getId());
            return ApiGatewayResponse.builder().setHeaders(origin).setStatusCode(200).setObjectBody(order).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiGatewayResponse.builder().setHeaders(origin).setStatusCode(500).setObjectBody(false).build();
    }
}
