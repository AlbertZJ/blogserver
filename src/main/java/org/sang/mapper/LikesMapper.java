package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.Likes;

/**
 * Created by albert on 2019/12/19.
 */
@Mapper
public interface LikesMapper {
    int add(Likes likes);

    Likes getLikes(Likes likes);

    int updatelike(Likes likes);
}
