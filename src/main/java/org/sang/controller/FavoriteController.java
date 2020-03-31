package org.sang.controller;

import org.sang.bean.Article;
import org.sang.bean.Favorite;
import org.sang.bean.Likes;
import org.sang.bean.RespBean;
import org.sang.service.FavoriteService;
import org.sang.service.LikesService;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by albert on 2019/12/19.
 */
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @RequestMapping(value = "/selected", method = RequestMethod.POST)
    public Favorite selected(Favorite favorite) {
        return favoriteService.selected(favorite);
    }

    @RequestMapping(value = "/selecteds", method = RequestMethod.POST)
    public Favorite selecteds(Favorite favorite) {
        return favoriteService.selecteds(favorite);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public int add(Favorite favorite) {
        return favoriteService.add(favorite);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Favorite> all() {
        return favoriteService.all();
    }

    @RequestMapping(value = "/delete{ids}", method = RequestMethod.DELETE)
    public RespBean deleted(@PathVariable String ids) {
        boolean result = favoriteService.deleted(ids);
        if (result) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/changed", method = RequestMethod.PUT)
    public RespBean updateFavorite(Favorite favorite) {
        int i = favoriteService.updateFavorite(favorite);
        if (i == 1) {
            return new RespBean("success", "收藏成功!");
        }
        return new RespBean("error", "收藏失败!");
    }

    @RequestMapping(value = "/changeds", method = RequestMethod.PUT)
    public RespBean updateFavorites(Favorite favorite) {
        int i = favoriteService.updateFavorite(favorite);
        if (i == 1) {
            return new RespBean("success", "取消收藏成功!");
        }
        return new RespBean("error", "取消收藏失败!");
    }
}
