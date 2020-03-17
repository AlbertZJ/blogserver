package org.sang.controller;


import org.sang.bean.*;
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
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    LikesService likesService;

    @RequestMapping(value = "/getlikes", method = RequestMethod.POST)
    public Likes getLikes(Likes likes) {

        return likesService.getLikes(likes);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RespBean deletelike(Likes likes) {
        int result = likesService.deletelike(likes);
        if (result==1) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean add( Likes likes,Long aid) {

        int result = likesService.add(likes);
        if (result == 1) {
            return new RespBean("success", "点赞成功!");
        }
        return new RespBean("error", "点赞失败!");
    }

@RequestMapping(value = "/adddislike", method = RequestMethod.POST)
public RespBean adddislike( Long aid) {

    int result = likesService.adddislike(aid);
    if (result == 1) {
        return new RespBean("success", "差评成功!");
    }
    return new RespBean("error", "差评失败!");
}
}

