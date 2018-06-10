package com.coiad.appservice.action;

import com.coiad.appservice.bean.RestFulBean;
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
    @RequestMapping(value="/loginByPwd", method= RequestMethod.GET)
    public RestFulBean<String> loginByPwd(@RequestParam String username, @RequestParam String password)
    {
        RestFulBean<String> restful = new RestFulBean<String>();
        restful.setData("hello, " + username + " welcom to my website!");
        restful.setStatus(0);
        restful.setMsg("成功");
        return restful;
    }
}
