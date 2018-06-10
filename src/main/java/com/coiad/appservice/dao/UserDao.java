package com.coiad.appservice.dao;

import com.coiad.appservice.bean.UserBean;

public interface UserDao {
    /**
     * 注册
     * @param userBean
     */
    UserBean register(UserBean userBean);

    /**
     * 登陆
     * @return
     */
    UserBean login(String phone, String password);

    /**
     * 根据名字获取用户信息
     * @param phone
     * @return
     */
    UserBean getUser(String phone);
}
