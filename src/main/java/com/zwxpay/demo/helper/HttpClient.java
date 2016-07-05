package com.zwxpay.demo.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * Created by zhangmeng on 16-7-4.
 */
public class HttpClient {
    private static Logger logger = Logger.getLogger(HttpClient.class);
    static CloseableHttpClient httpClient = null;
    static {
        try{
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    builder.build());
            httpClient = HttpClients.custom().setSSLSocketFactory(
                    sslsf).build();
        }catch(Exception e){
            logger.error("", e);
            httpClient = HttpClients.createDefault();
        }
    }

    public static JsonNode doPost(CommonD url, JsonNode json){
        CloseableHttpResponse response = null;
        try{
            String s = JsonHelper.stringify(json);
            logger.info("req: " + s);
            StringEntity stringEntity = new StringEntity(JsonHelper.stringify(json), Consts.UTF_8);
            HttpPost post = new HttpPost(url.getValue());
            post.setEntity(stringEntity);
            post.setHeader("Content-Type", "text/xml; charset=utf-8");
            post.setHeader("X-FORMAT", "json");
            response = httpClient.execute(post);
            String respStr = EntityUtils.toString(response.getEntity());
            JsonNode parse = JsonHelper.parse(respStr);
            logger.info("resp: " + parse);
            return parse;
        }catch(Exception e){
            logger.error("", e);
            return JsonHelper.toJson(ImmutableMap.of("message", e.getMessage()));
        }finally {
            if(response != null) try {response.close(); } catch(Exception ignore) { ignore.printStackTrace(); };
        }
    }
}
