package org.sang.controller;

import org.sang.bean.Category;
import org.sang.bean.ComLikes;
import org.sang.bean.Likes;
import org.sang.bean.RespBean;
import org.sang.service.ComLikesService;
import org.sang.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 超级管理员专属Controller
 */
@RestController
@RequestMapping("/comlikes")
public class ComlikesController {
    @Autowired
    ComLikesService comlikesService;

    @RequestMapping(value = "/getlikes", method = RequestMethod.POST)
    public ComLikes getLikes(ComLikes likes) {
        return comlikesService.getLikes(likes);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RespBean deleteById(Long cid,int likestate) {
        int result = comlikesService.deletelike(cid,likestate);
        if (result==1) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean add(Long cid,int likestate) {
        int result = comlikesService.add(cid,likestate);
        if (result == 1) {
            return new RespBean("success", "点赞成功!");
        }
        return new RespBean("error", "点赞失败!");
    }

    @RequestMapping(value = "/adddislike", method = RequestMethod.POST)
    public RespBean adddislike(Long  cid,int likestate) {
        int result = comlikesService.add(cid,likestate);
        if (result == 1) {
            return new RespBean("success", "差评成功!");
        }
        return new RespBean("error", "差评失败!");
    }
}

