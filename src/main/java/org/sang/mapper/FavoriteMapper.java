package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.Category;
import org.sang.bean.Favorite;
import org.sang.bean.Likes;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    int add(Favorite favorite);
    Favorite selected(Favorite favorite);
    Favorite selecteds(Favorite favorite);
    List<Favorite> all(Favorite favorite);
    int deleted(@Param("ids") String[] ids);
    int updateFavorite(Favorite favorite);
}
