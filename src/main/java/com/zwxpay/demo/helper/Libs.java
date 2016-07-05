package com.zwxpay.demo.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import com.zwxpay.demo.conf.CommonD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 * Created by zhangmeng on 16-7-5.
 */
public class Libs {

    //组装数据
    public static JsonNode buildData(JsonNode jn){
        Map<String, Object> map = JsonHelper.fromJson(jn, Map.class);
        map.put("mch_id", CommonD.MCH_ID.getValue());
        map.put("nonce_str", getRandomStringByLength(20));
        map.put("sign", getSign(map));
        return JsonHelper.toJson(map);
    }

    //获取随机数
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //签名
    public static String getSign(Map<String,Object> map){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(!entry.getValue().equals("")){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + CommonD.KEY.getValue();
        System.out.println("Sign Before MD5:" + result);
        result = MD5.MD5Encode(result).toUpperCase();
        System.out.println("Sign Result:" + result);
        return result;
    }

    public static void main(String[] args){
        Map<String, Object> map = Maps.newHashMap();
        map.put("aa", "AA");
        map.put("bb", "BB");
        getSign(map);
        System.out.println(getRandomStringByLength(10));
    }

}
