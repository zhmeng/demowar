package com.zwxpay.demo.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import com.zwxpay.demo.helper.Libs;
import com.zwxpay.demo.helper.Tuple;
import com.zwxpay.demo.payment.ScanCodePay;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by zhangmeng on 16-7-4.
 * 进行调用的服务类
 */
public class InvokeCommon {

    private static Logger logger = Logger.getLogger(InvokeCommon.class);
    public static InvokeCommon instance = new InvokeCommon();

    Map<String, Tuple> services = Maps.newConcurrentMap();


    //将所有服务初始化,反射调用
    private InvokeCommon(){
        ScanCodePay single = new ScanCodePay();
        try{
            this.registerService("business/views/order", single.getClass().getMethod("payUnify", JsonNode.class), single);
            this.registerService("business/views/queryOrder", single.getClass().getMethod("payQuery", JsonNode.class), single);
            this.registerService("business/views/refund", single.getClass().getMethod("refund", JsonNode.class), single);
            this.registerService("business/views/queryRefund", single.getClass().getMethod("refundQuery", JsonNode.class), single);
            this.registerService("business/views/scancode/order", single.getClass().getMethod("payUnify", JsonNode.class), single);
            this.registerService("business/views/scancode/queryOrder", single.getClass().getMethod("payQuery", JsonNode.class), single);
            this.registerService("business/views/scancode/refund", single.getClass().getMethod("refund", JsonNode.class), single);
            this.registerService("business/views/scancode/queryRefund", single.getClass().getMethod("refundQuery", JsonNode.class), single);
        }catch(Exception e){
            logger.error("", e);
        }
    }

    public void registerService(String key, Method m, Object obj){
        services.put(key, new Tuple(m, obj));
    }


    //服务调用
    public JsonNode invokeByMethod(String key, JsonNode params){
        JsonNode postPara = Libs.buildData(params); //组装完整数据
        Tuple tuple = services.get(key);
        Method method = (Method) tuple._1();
        try{
            Object invoke = method.invoke(tuple._2(), postPara); //进行对应业务逻辑调用
            return (JsonNode)invoke;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
