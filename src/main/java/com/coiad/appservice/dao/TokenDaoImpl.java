package com.coiad.appservice.dao;

import com.coiad.appservice.bean.TokenBean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TokenDaoImpl extends HibernateDaoSupport implements TokenDao {

    public void saveOrUpdageToken(TokenBean tokenBean) {
        this.getHibernateTemplate().save(tokenBean);
    }

    public TokenBean isTokenAvailable(String phone) {
        List<TokenBean> list = (List<TokenBean>) this.getHibernateTemplate()
                .find("from TokenBean where phone=?", phone);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public boolean getExpiredDate(String phone) {
        List<TokenBean> list = (List<TokenBean>) this.getHibernateTemplate()
                .find("from TokenBean where phone=?", phone);
        if (list != null && list.size() > 0) {
            return compareDate(new Date(), list.get(0).getExpiredDate());
        }
        return false;
    }

    private boolean compareDate(Date nowDate, Date expiredDate) {
        Calendar now = Calendar.getInstance();
        Calendar expired = Calendar.getInstance();
        now.setTime(nowDate);
        expired.setTime(expiredDate);
        return now.before(expired);
    }
}
