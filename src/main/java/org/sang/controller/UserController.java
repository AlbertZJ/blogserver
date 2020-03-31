package org.sang.controller;

import org.sang.bean.RespBean;
import org.sang.bean.User;
import org.sang.service.UserService;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by albert on 2019/12/19.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public List<User> getUserAll(String nickname) {
        return userService.getUserAll(nickname);
    }

    @RequestMapping("/currentUserName")
    public String currentUserName() {
        return Util.getCurrentUser().getNickname();
    }

    @RequestMapping("/currentUserId")
    public Long currentUserId() {
        return Util.getCurrentUser().getId();
    }

    @RequestMapping("/currentUserEmail")
    public String currentUserEmail() {
        return Util.getCurrentUser().getEmail();
    }

    @RequestMapping("/isArticle")
    public Boolean isArticle() {
        List<GrantedAuthority> authorities = Util.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("文章管理")) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping("/isComment")
    public Boolean isComment() {
        List<GrantedAuthority> authorities = Util.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("留言管理")) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping("/isNotice")
    public Boolean isNotice() {
        List<GrantedAuthority> authorities = Util.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("通知管理")) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping("/isAdmin")
    public Boolean isAdmin() {
        List<GrantedAuthority> authorities = Util.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("超级管理员")) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/updateUserEmail", method = RequestMethod.PUT)
    public RespBean updateUserEmail(String email) {
        if (userService.updateUserEmail(email) == 1) {
            return new RespBean("success", "开启成功!");
        }
        return new RespBean("error", "开启失败!");
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public RespBean updateUser(User user) {
        int result = userService.updateUserById(user);
        if (result == 1) {
            //成功
            return new RespBean("success", "修改成功!");
        } else {
            //失败
            return new RespBean("error", "修改失败!");
        }
    }

    @RequestMapping(value = "/updatePwd", method = RequestMethod.PUT)
    public RespBean updatePwd(User user) {
        int result = userService.updatePwdById(user);
        if (result == 1) {
            //成功
            return new RespBean("success", "修改成功!");
        } else {
            //失败
            return new RespBean("error", "修改失败!");
        }
    }
}
