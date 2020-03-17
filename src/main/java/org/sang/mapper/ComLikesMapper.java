package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.ComLikes;

@Mapper
public interface ComLikesMapper {
    int add(ComLikes likes);
    ComLikes getLikes(ComLikes likes);
    int deletelike(ComLikes likes);
     ComLikes number(ComLikes comLikes);
     int deleted(ComLikes comLikes);
}
