package com.coiad.appservice.action;

import com.coiad.appservice.bean.AnniversaryBean;
import com.coiad.appservice.bean.RestFulBean;
import com.coiad.appservice.service.AnniversaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/data")
public class AnniversaryAction {

    @Autowired
    private AnniversaryService anniversaryService;

    @ResponseBody
    @RequestMapping(value = "/addInfo", method = RequestMethod.POST)
    public RestFulBean<Boolean> addInfo(AnniversaryBean bean) {
        return anniversaryService.addAnniversary(bean);
    }

    @ResponseBody
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public RestFulBean<Boolean> updateInfo(AnniversaryBean bean) {
        return anniversaryService.updateAnniversary(bean);
    }

    @ResponseBody
    @RequestMapping(value = "/delInfo", method = RequestMethod.GET)
    public RestFulBean<Boolean> delInfo(int id) {
        return anniversaryService.delAnniversary(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public RestFulBean<AnniversaryBean> getInfo(int id) {
        return anniversaryService.getAnniversary(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getInfoList", method = RequestMethod.GET)
    public RestFulBean<List<AnniversaryBean>> getInfoList() {
        return anniversaryService.getAnniversaryList();
    }
}
