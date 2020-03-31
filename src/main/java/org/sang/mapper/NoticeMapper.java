package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.Article;
import org.sang.bean.Notice;
import org.sang.bean.NoticeUser;

import java.util.List;

/**
 * Created by albert on 2019/12/19.
 */
@Mapper
public interface NoticeMapper {

    int addNewNotice(Notice notice);

    int updateNotice(Notice notice);

    int updateNews(Notice notice);

    Notice show(Notice notice);

    Notice sys(Notice notice);

    List<Notice> getNoticeByState(@Param("state") Integer state, @Param("start") Integer start, @Param("count") Integer count, @Param("uid") Long uid, @Param("keywords") String keywords);

//    List<Article> getArticleByStateByAdmin(@Param("start") int start, @Param("count") Integer count, @Param("keywords") String keywords);

    int getNoticeCountByState(@Param("state") Integer state, @Param("uid") Long uid, @Param("keywords") String keywords);

    int updateNoticeState(@Param("aids") Long aids[], @Param("state") Integer state);

    int updateNoticeStateById(@Param("noticeId") Integer noticeId, @Param("state") Integer state);

    int deleteNoticeById(@Param("aids") Long[] aids);

    Notice getNoticeById(Long aid);

    List<Integer> getDataStatistics(Long uid);
}