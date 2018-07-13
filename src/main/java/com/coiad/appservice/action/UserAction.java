package com.coiad.appservice.action;

import com.coiad.appservice.bean.RestFulBean;
import com.coiad.appservice.bean.UserBean;
import com.coiad.appservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserAction {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/loginByPwd", method = RequestMethod.GET)
    public RestFulBean<UserBean> loginByPwd(@RequestParam String phone, @RequestParam String password) {
        return userService.login(phone, password);
    }
}
