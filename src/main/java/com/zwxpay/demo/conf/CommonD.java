package com.zwxpay.demo.conf;

/**
 * Created by zhangmeng on 16-7-4.
 */
public enum CommonD {
    MCH_ID("15121009"), //商户编号
    KEY("0d8226a1fe3cd661308f9e71e8c859db"),  //商户key
    PAY_URL("https://api.zwxpay.com/pay/unifiedorder"), //支付URL
    PAYQRY_URL("https://api.zwxpay.com/pay/orderquery"), // 支付查询URL
    REFUND_URL("https://api.zwxpay.com/secapi/pay/refund"), //退款URL
    REFUNDQRY_URL("https://api.zwxpay.com/pay/refundquery"); //退款查询URL

    private String value;
    private CommonD(String s){
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}