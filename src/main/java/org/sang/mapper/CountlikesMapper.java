package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.Countlikes;

/**
 * Created by albert on 2019/12/19.
 */
@Mapper
public interface CountlikesMapper {
    int add(Countlikes countlikes);

    int update(Countlikes countlikes);

    int delete(Countlikes countlikes);

    Countlikes selects(Countlikes countlikes);

    int updatedelete(Countlikes countlikes);

    int updatedislike(Countlikes countlikes);

    int deletedislike(Countlikes countlikes);

}
