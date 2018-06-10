package com.coiad.appservice.util;

import com.coiad.appservice.bean.RestFulBean;

public class RestFulUtil<T> {
    private static RestFulUtil instance = null;
    private RestFulUtil(){}

    public static RestFulUtil getInstance() {
        if (instance == null) {
            instance = new RestFulUtil();
        }
        return instance;
    }

    public RestFulBean<T> getRestFulBean(T o, int status, String msg) {
        RestFulBean<T> objectRestFulBean = new RestFulBean<T>();
        objectRestFulBean.setData(o);
        objectRestFulBean.setMsg(msg);
        objectRestFulBean.setStatus(status);
        return objectRestFulBean;
    }

}
