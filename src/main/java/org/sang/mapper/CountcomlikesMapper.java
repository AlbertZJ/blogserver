package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.Countcomlikes;
import org.sang.bean.Countlikes;

@Mapper
public interface CountcomlikesMapper {
    int add(Countcomlikes countlikes);

    int update(Countcomlikes countlikes);
    int delete(Countcomlikes countlikes);
    Countcomlikes selects(Countcomlikes countlikes);
    int updatedelete(Countcomlikes countlikes);
    int updatedislike(Countcomlikes countlikes);
    int deletedislike(Countcomlikes countlikes);

}
