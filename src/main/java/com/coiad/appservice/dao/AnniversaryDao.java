package com.coiad.appservice.dao;

import com.coiad.appservice.bean.AnniversaryBean;

import java.util.List;

public interface AnniversaryDao {

    AnniversaryBean getAnniversary(int id, String phone);

    boolean saveAnniversary(AnniversaryBean bean);

    boolean updateAnniversary(AnniversaryBean bean);

    boolean deleteAnniversary(int id);

    List<AnniversaryBean> getAnniList(String phone);

    List<AnniversaryBean> getAllList();
}
