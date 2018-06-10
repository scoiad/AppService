package com.coiad.appservice.service;

import com.coiad.appservice.bean.RestFulBean;
import com.coiad.appservice.bean.UserBean;
import com.coiad.appservice.dao.UserDao;
import com.coiad.appservice.util.RestFulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public RestFulBean<UserBean> registorServer(UserBean userBean) {
        UserBean user = userDao.getUser(userBean.getPhone());
        if (user != null) {
            return RestFulUtil.getInstance().getRestFulBean(null, 1, "已经注册过了");
        }
        else {
            user = userDao.register(userBean);
            if (user != null) {
                return RestFulUtil.getInstance().getRestFulBean(user, 0, "注册成功");
            }else {
                return RestFulUtil.getInstance().getRestFulBean(null, 0, "注册失败");
            }
        }
    }

    public RestFulBean<UserBean> login(String phone, String password) {
        UserBean user = userDao.login(phone, password);
        if (user != null) {
            return RestFulUtil.getInstance().getRestFulBean(user, 0, "登录成功");
        }else {
            return RestFulUtil.getInstance().getRestFulBean(null, 1, "登录失败");
        }
    }
}
