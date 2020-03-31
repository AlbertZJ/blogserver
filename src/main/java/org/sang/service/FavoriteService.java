package org.sang.service;

import org.sang.bean.Category;
import org.sang.bean.Countlikes;
import org.sang.bean.Favorite;
import org.sang.bean.Likes;
import org.sang.mapper.CountlikesMapper;
import org.sang.mapper.FavoriteMapper;
import org.sang.mapper.LikesMapper;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by albert on 2019/12/19.
 */
@Service
@Transactional
public class FavoriteService {

    @Autowired
    FavoriteMapper favoriteMapper;

    public Favorite selected(Favorite favorite) {
        //设置当前用户(通过当前用户id)
        favorite.setUid(Util.getCurrentUser().getId());
//        favorite.setPublishDate(new Timestamp(System.currentTimeMillis()));
//        favoriteMapper.add(favorite);
        Favorite l = favoriteMapper.selected(favorite);
        return l;
    }

    public Favorite selecteds(Favorite favorite) {
        //设置当前用户(通过当前用户id)
        favorite.setUid(Util.getCurrentUser().getId());
//        favorite.setPublishDate(new Timestamp(System.currentTimeMillis()));
//        favoriteMapper.add(favorite);
        Favorite l = favoriteMapper.selecteds(favorite);
        return l;
    }

    public int add(Favorite favorite) {
        //设置当前用户(通过当前用户id)
        favorite.setUid(Util.getCurrentUser().getId());
        favorite.setPublishDate(new Timestamp(System.currentTimeMillis()));
        int l = favoriteMapper.add(favorite);
        return l;
    }

    public List<Favorite> all() {
        Long uid = Util.getCurrentUser().getId();
        Favorite favorite = new Favorite();
        favorite.setPublishDate(new Timestamp(System.currentTimeMillis()));
        favorite.setUid(uid);
        return favoriteMapper.all(favorite);
    }

    public boolean deleted(String ids) {
        String[] split = ids.split(",");
        int result = favoriteMapper.deleted(split);
        return result == split.length;
    }

    public int updateFavorite(Favorite favorite) {
        favorite.setPublishDate(new Timestamp(System.currentTimeMillis()));
        favorite.setUid(Util.getCurrentUser().getId());
        return favoriteMapper.updateFavorite(favorite);
    }
}
