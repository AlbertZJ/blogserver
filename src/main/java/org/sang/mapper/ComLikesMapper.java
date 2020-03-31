package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.ComLikes;

/**
 * Created by albert on 2019/12/19.
 */
@Mapper
public interface ComLikesMapper {
    int add(ComLikes likes);

    ComLikes getLikes(ComLikes likes);

    int deletelike(ComLikes likes);

    ComLikes number(ComLikes comLikes);

    int deleted(ComLikes comLikes);
}
