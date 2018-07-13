package com.coiad.appservice.dao;

import com.coiad.appservice.bean.TokenBean;

public interface TokenDao {

    void saveOrUpdageToken(TokenBean tokenBean);

    TokenBean isTokenAvailable(String phone);

    boolean getExpiredDate(String phone);
}
