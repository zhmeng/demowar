package com.zwxpay.demo.helper;

/**
 * Created by zhangmeng on 16-7-4.
 */
public enum CommonD {
    MCH_ID("15121009"),
    KEY("0d8226a1fe3cd661308f9e71e8c859db"),
    PAY_URL("https://api.zwxpay.com/pay/unifiedorder"),
    PAYQRY_URL("https://api.zwxpay.com/pay/orderquery"),
    REFUND_URL("https://api.zwxpay.com/secapi/pay/refund"),
    REFUNDQRY_URL("https://api.zwxpay.com/pay/refundquery");

    private String value;
    private CommonD(String s){
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}