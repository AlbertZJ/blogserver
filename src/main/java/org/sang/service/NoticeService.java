package org.sang.service;



import org.sang.bean.Notice;
import org.sang.bean.NoticeUser;
import org.sang.mapper.NoticeMapper;
import org.sang.mapper.TagsMapper;
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
public class NoticeService {

    @Autowired
    NoticeMapper NoticeMapper;
    @Autowired
    TagsMapper tagsMapper;

    public int addNewNotice(Notice notice) {
        //处理文章摘要
//        if (notice.getSummary() == null || "".equals(notice.getSummary())) {
//            //直接截取
//            String stripHtml = stripHtml(notice.getHtmlContent());
//            notice.setSummary(stripHtml.substring(0, stripHtml.length() > 50 ? 50 : stripHtml.length()));
//        }
        if (notice.getId() == -1) {
            //添加操作
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (notice.getState() == 1) {
                //设置发表日期
                notice.setPublishDate(timestamp);
            }
            //   notice.setEditTime(timestamp);
            //设置当前用户
            notice.setUid(Util.getCurrentUser().getId());
            int i = NoticeMapper.addNewNotice(notice);
            //打标签
            // String[] dynamicTags = notice.getDynamicTags();
//            if (dynamicTags != null && dynamicTags.length > 0) {
//                int tags = addTagsToArticle(dynamicTags, notice.getId());
//                if (tags == -1) {
//                    return tags;
//                }
//            }
            return i;
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (notice.getState() == 1) {
                //设置发表日期
                notice.setPublishDate(timestamp);
            }
            //更新
            // notice.setEditTime(new Timestamp(System.currentTimeMillis()));
            int i = NoticeMapper.updateNotice(notice);
            //修改标签
            // String[] dynamicTags = notice.getDynamicTags();
//            if (dynamicTags != null && dynamicTags.length > 0) {
//                int tags = addTagsToArticle(dynamicTags, notice.getId());
//                if (tags == -1) {
//                    return tags;
//                }
//            }
            return i;
        }
    }



    public List<Notice> getNoticeByState(Integer state, Integer page, Integer count,String keywords) {
        int start = (page - 1) * count;
        Long uid = Util.getCurrentUser().getId();
        return NoticeMapper.getNoticeByState(state, start, count, uid,keywords);
    }

//    public List<Article> getArticleByStateByAdmin(Integer page, Integer count,String keywords) {
//        int start = (page - 1) * count;
//        return articleMapper.getArticleByStateByAdmin(start, count,keywords);
//    }

    public int getNoticeCountByState(Integer state, Long uid,String keywords) {
        return NoticeMapper.getNoticeCountByState(state, uid,keywords);
    }

    public int updateNoticeState(Long[] aids, Integer state) {
        if (state == 2) {
            return NoticeMapper.deleteNoticeById(aids);
        } else {
            return NoticeMapper.updateNoticeState(aids, 2);//放入到回收站中
        }
    }

    public int restoreNotice(Integer noticeId) {
        return NoticeMapper.updateNoticeStateById(noticeId, 1); // 从回收站还原在原处
    }

    public Notice getNoticeById(Long aid) {
        Notice notice = NoticeMapper.getNoticeById(aid);
        // NoticeMapper.pvIncrement(aid);
        return notice;
    }


    /**
     * 获取最近七天的日期
     * @return
     */
    //  public List<String> getCategories() {
//        return NoticesMapper.getCategories(Util.getCurrentUser().getId());
//    }


    /**
     * 获取最近七天的数据
     * @return
     */
    public List<Integer> getDataStatistics() {
        return NoticeMapper.getDataStatistics(Util.getCurrentUser().getId());
    }
}

//    @Autowired
//    NoticeMapper noticeMapper;
//
//    public List<NoticeUser> findAll() {
//        return noticeMapper.findAll();
//    }
//
//    public boolean deleteByIds(String ids) {
//        Notice notice = null;
//        notice.setDeleteTime(new Timestamp(System.currentTimeMillis()));
//        String[] split = ids.split(",");
//        int result = noticeMapper.deleteByIds(split);
//        return result == split.length;
//    }
//
//    public int updateById(Notice notice) {
//        return noticeMapper.updateById(notice);
//    }
//
//    public int add(Notice notice) {
//        notice.setPublishDate(new Timestamp(System.currentTimeMillis()));
//        return noticeMapper.add(notice);
//    }
//}
