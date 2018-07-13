package com.coiad.appservice.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.coiad.appservice.bean.RestFulBean;
import com.coiad.appservice.bean.TokenBean;
import com.coiad.appservice.dao.TokenDao;
import com.coiad.appservice.util.RestFulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RestFulInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenDao tokenDao;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = httpServletRequest.getHeader(key);
            map.put(key, value);
        }
        if (!uri.contains("/data/")) {
            return true;
        } else {
            TokenBean tokenBean = tokenDao.isTokenAvailable(map.get("phone"));
            boolean isExpired = tokenDao.getExpiredDate(map.get("phone"));
            if (tokenBean != null && tokenBean.getToken().equals(map.get("token")) && isExpired) {
                return true;
            } else {
                RestFulBean<TokenBean> resuFulBean = RestFulUtil.getInstance().getRestFulBean(null, 1, "用户身份验证失败");
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                Writer writer = httpServletResponse.getWriter();
                writer.write(JSONObject.toJSONString(resuFulBean, SerializerFeature.WriteMapNullValue));
                return false;
            }
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
