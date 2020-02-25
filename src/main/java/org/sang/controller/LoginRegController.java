package org.sang.controller;

import org.sang.bean.RespBean;
import org.sang.bean.User;
import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by albert on 2019/12/20.
 */
@Controller
public class LoginRegController {

    @Autowired
    UserService userService;

    @RequestMapping("/login_error")
    @ResponseBody
    public RespBean loginError() {

        System.out.println("sahf");
        return new RespBean("error", "登录失败sfg!");
    }

    @RequestMapping("/login_success")
    @ResponseBody
    public RespBean loginSuccess()
    {

        return new RespBean("success", "登录成功s!");
    }

    /**
     * 如果自动跳转到这个页面，说明用户未登录，返回相应的提示即可
     * <p>
     * 如果要支持表单登录，可以在这个方法中判断请求的类型，进而决定返回JSON还是HTML页面
     *
     * @return
     */
    @RequestMapping("/login_page")
    public RespBean loginPage() {
        return new RespBean("error", "尚未登录，请登录!");
    }

}
