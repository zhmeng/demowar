package com.zwxpay.demo.payment;

import com.fasterxml.jackson.databind.JsonNode;
import com.zwxpay.demo.helper.HttpClient;
import org.apache.log4j.Logger;

import static com.zwxpay.demo.conf.CommonD.*;

/**
 * Created by zhangmeng on 16-7-4.
 */
public class ScanCodePay implements Pay{
    private static Logger logger = Logger.getLogger(ScanCodePay.class);

    public JsonNode payUnify(JsonNode req) {
        return HttpClient.doPost(PAY_URL, req);
    }

    public JsonNode payQuery(JsonNode req) {
        return HttpClient.doPost(PAYQRY_URL, req);
    }

    public JsonNode refund(JsonNode req) {
        return HttpClient.doPost(REFUND_URL, req);
    }

    public JsonNode refundQuery(JsonNode req) {
        return HttpClient.doPost(REFUNDQRY_URL, req);
    }
}
