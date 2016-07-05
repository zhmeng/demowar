package com.zwxpay.demo.payment;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by zhangmeng on 16-7-4.
 */
public interface Pay {

    JsonNode payUnify(JsonNode req);  // 下单
    JsonNode payQuery(JsonNode req);   // 支付结果查询
    JsonNode refund(JsonNode req); //退款
    JsonNode refundQuery(JsonNode req); //退款查询
}
