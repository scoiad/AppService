package com.coiad.appservice.dao;

import com.coiad.appservice.bean.AnniversaryBean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class AnniversaryDaoImpl extends HibernateDaoSupport implements AnniversaryDao {

    public AnniversaryBean getAnniversary(int id) {
        List<AnniversaryBean> list = (List<AnniversaryBean>) this.getHibernateTemplate()
                .find("from AnniversaryBean where id = ?", id);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public boolean saveAnniversary(AnniversaryBean bean) {
        int id = (Integer) this.getHibernateTemplate().save(bean);
        return getAnniversary(id) != null;
    }

    public boolean updateAnniversary(AnniversaryBean bean) {
        try {
            this.getHibernateTemplate().update(bean);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteAnniversary(int id) {
        try {
            this.getHibernateTemplate().delete("id", id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<AnniversaryBean> getAnniList() {
        return (List<AnniversaryBean>) this.getHibernateTemplate().find("from AnniversaryBean");
    }
}
