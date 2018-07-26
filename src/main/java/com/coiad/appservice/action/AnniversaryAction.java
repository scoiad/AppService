package com.coiad.appservice.action;

import com.coiad.appservice.bean.AnniversaryBean;
import com.coiad.appservice.bean.RestFulBean;
import com.coiad.appservice.service.AnniversaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/data")
public class AnniversaryAction {

    @Autowired
    private AnniversaryService anniversaryService;

    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @RequestMapping(value = "/addInfo", method = RequestMethod.PUT)
    public RestFulBean<Boolean> addInfo(@RequestBody AnniversaryBean bean) {
        bean.setPhone(request.getHeader("phone"));
        return anniversaryService.addAnniversary(bean);
    }

    @ResponseBody
    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public RestFulBean<Boolean> updateInfo(@RequestBody AnniversaryBean bean) {
        bean.setPhone(request.getHeader("phone"));
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
        String phone = request.getHeader("phone");
        return anniversaryService.getAnniversary(id, phone);
    }

    @ResponseBody
    @RequestMapping(value = "/getInfoList", method = RequestMethod.GET)
    public RestFulBean<List<AnniversaryBean>> getInfoList() {
        String phone = request.getHeader("phone");
        return anniversaryService.getAnniversaryList(phone);
    }
}
