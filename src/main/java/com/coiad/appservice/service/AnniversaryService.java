package com.coiad.appservice.service;

import com.coiad.appservice.bean.AnniversaryBean;
import com.coiad.appservice.bean.RestFulBean;
import com.coiad.appservice.dao.AnniversaryDao;
import com.coiad.appservice.util.RestFulUtil;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Transactional
public class AnniversaryService {

    @Autowired
    private AnniversaryDao anniversaryDao;

    public RestFulBean<Boolean> addAnniversary(AnniversaryBean bean) {
        if (bean.isHundredFlag()) {
            calHundredDay(bean);
        }
        boolean success = anniversaryDao.saveAnniversary(bean);
        if (success) {
            return RestFulUtil.getInstance().getRestFulBean(success, 0, "新增成功");
        } else {
            return RestFulUtil.getInstance().getRestFulBean(success, 1, "新增失败");
        }
    }

    public RestFulBean<Boolean> updateAnniversary(AnniversaryBean bean) {
        if (bean.isHundredFlag()) {
            calHundredDay(bean);
        }

        boolean success = anniversaryDao.updateAnniversary(bean);
        if (success) {
            return RestFulUtil.getInstance().getRestFulBean(success, 0, "修改成功");
        } else {
            return RestFulUtil.getInstance().getRestFulBean(success, 1, "修改失败");
        }
    }

    public RestFulBean<Boolean> delAnniversary(int id) {
        boolean success = anniversaryDao.deleteAnniversary(id);
        if (success) {
            return RestFulUtil.getInstance().getRestFulBean(success, 0, "删除成功");
        } else {
            return RestFulUtil.getInstance().getRestFulBean(success, 1, "删除失败");
        }
    }

    public RestFulBean<AnniversaryBean> getAnniversary(int id) {
        AnniversaryBean anniversaryBean = anniversaryDao.getAnniversary(id);
        if (anniversaryBean != null) {
            return RestFulUtil.getInstance().getRestFulBean(anniversaryBean, 0, "查询成功");
        } else {
            return RestFulUtil.getInstance().getRestFulBean(null, 1, "查询失败");
        }
    }

    public RestFulBean<List<AnniversaryBean>> getAnniversaryList() {
        List<AnniversaryBean> mList = anniversaryDao.getAnniList();
        if (mList != null) {
            return RestFulUtil.getInstance().getRestFulBean(mList, 0, "查询成功");
        } else {
            return RestFulUtil.getInstance().getRestFulBean(null, 1, "查询失败");
        }
    }

    private void calHundredDay(@NotNull AnniversaryBean bean) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bean.getAnniversaryDate());
        calendar.add(Calendar.DATE, 100);
        bean.setHundredDate(calendar.getTime());
    }
}
