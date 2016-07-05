package com.zwxpay.demo.servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.zwxpay.demo.helper.HttpClient;
import com.zwxpay.demo.helper.InvokeCommon;
import com.zwxpay.demo.helper.JsonHelper;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangmeng on 16-7-4.
 */
public class MethodInvoke extends HttpServlet {
    private Logger logger = Logger.getLogger(MethodInvoke.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>HELLO</div>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> params = Maps.newHashMap();
        List<String> ignores = ImmutableList.of("method");
        Enumeration parameterNames = req.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String key = String.valueOf(parameterNames.nextElement());
            params.put(key, req.getParameter(key));
        }
        logger.info("init d:" + params);
        String method = params.get("method");
        for(String ignore: ignores){
            params.remove(ignore);
        }
        JsonNode jsonNode = InvokeCommon.instance.invokeByMethod(method, JsonHelper.toJson(params));
        logger.info(jsonNode);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.write(String.valueOf(jsonNode));
    }
}
