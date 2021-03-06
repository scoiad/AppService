package com.coiad.appservice.service;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.coiad.appservice.bean.RestFulBean;
import com.coiad.appservice.bean.TokenBean;
import com.coiad.appservice.bean.UserBean;
import com.coiad.appservice.dao.TokenDao;
import com.coiad.appservice.dao.UserDao;
import com.coiad.appservice.util.MD5;
import com.coiad.appservice.util.RestFulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Transactional
public class UserService {

    private final static String MASTER_SECRET = "6225095981be8c4af13371d5";
    private final static String APP_KEY = "48759b1b893c001db450f338";

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    public RestFulBean<UserBean> registorServer(UserBean userBean) {
        UserBean user = userDao.getUser(userBean.getPhone());
        if (user != null) {
            return RestFulUtil.getInstance().getRestFulBean(null, 1, "已经注册过了");
        } else {
            user = userDao.register(userBean);
            if (user != null) {
                return RestFulUtil.getInstance().getRestFulBean(user, 0, "注册成功");
            } else {
                return RestFulUtil.getInstance().getRestFulBean(null, 0, "注册失败");
            }
        }
    }

    public RestFulBean<UserBean> login(String phone, String password) {

        UserBean user = userDao.login(phone, password);
        if (user != null) {
            saveOrUpdateToken(user);
            return RestFulUtil.getInstance().getRestFulBean(user, 0, "登录成功");
        } else {
            return RestFulUtil.getInstance().getRestFulBean(null, 1, "登录失败");
        }
    }

    private void saveOrUpdateToken(UserBean userBean) {
        String token = null;
        try {
            token = MD5.encryptMD5(String.valueOf(System.currentTimeMillis()) + userBean.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
        }
        userBean.setToken(token);
        TokenBean tokenBean = tokenDao.isTokenAvailable(userBean.getPhone());
        if (tokenBean != null) {
            tokenBean.setToken(token);
        } else {
            tokenBean = new TokenBean();
            tokenBean.setPhone(userBean.getPhone());
            tokenBean.setToken(token);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 7);
        tokenBean.setExpiredDate(calendar.getTime());
        tokenDao.saveOrUpdageToken(tokenBean);
    }
}
