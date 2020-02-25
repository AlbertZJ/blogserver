package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.Likes;

@Mapper
public interface LikesMapper {
    int add(Likes likes);
    Likes getLikes(Likes likes);
    int deletelike(Likes likes);
}
