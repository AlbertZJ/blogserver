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
    NoticeMapper noticeMapper;
    @Autowired
    TagsMapper tagsMapper;

    public int addNewNotice(Notice notice) {
        //处理通知摘要
        if (notice.getMessage() == null || "".equals(notice.getMessage())) {
            //直接截取
            String stripHtml = stripHtml(notice.getHtmlContent());
            notice.setMessage(stripHtml.substring(0, stripHtml.length() > 50 ? 50 : stripHtml.length()));
        }
        if (notice.getId() == -1) {
            //添加操作
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (notice.getState() == 1) {
                //设置发表日期
                notice.setPublishDate(timestamp);
            }else if(notice.getState() == 0){
                notice.setPublishDate(timestamp);
            }

            //   notice.setEditTime(timestamp);
            //设置当前用户
            notice.setUid(Util.getCurrentUser().getId());
            if(notice.getNews().equals("是")){
                noticeMapper.updateNews(notice);
            }
            int i = noticeMapper.addNewNotice(notice);
            return i;
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            notice.setUid(Util.getCurrentUser().getId());
            if (notice.getState() == 1) {
                //设置发表日期
                notice.setPublishDate(timestamp);
            }
            if(notice.getNews().equals("是")){
                noticeMapper.updateNews(notice);
            }
            //更新
            // notice.setEditTime(new Timestamp(System.currentTimeMillis()));
            int i = noticeMapper.updateNotice(notice);
            return i;
        }
    }

    public String stripHtml(String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

    public List<Notice> getNoticeByState(Integer state, Integer page, Integer count,String keywords) {
        int start = (page - 1) * count;
        Long uid = Util.getCurrentUser().getId();
        return noticeMapper.getNoticeByState(state, start, count, uid,keywords);
    }

//    public List<notice> getnoticeByStateByAdmin(Integer page, Integer count,String keywords) {
//        int start = (page - 1) * count;
//        return noticeMapper.getnoticeByStateByAdmin(start, count,keywords);
//    }

    public int getNoticeCountByState(Integer state, Long uid,String keywords) {
        return noticeMapper.getNoticeCountByState(state, uid,keywords);
    }

    public int updateNoticeState(Long[] aids, Integer state) {
        if (state == 2) {
            return noticeMapper.deleteNoticeById(aids);
        } else {
            return noticeMapper.updateNoticeState(aids, 2);//放入到回收站中
        }
    }

    public int restoreNotice(Integer noticeId) {
        return noticeMapper.updateNoticeStateById(noticeId, 1); // 从回收站还原在原处
    }

    public Notice getNoticeById(Long aid) {
        Notice notice = noticeMapper.getNoticeById(aid);
        // NoticeMapper.pvIncrement(aid);
        return notice;
    }

public Notice show(){
        Notice notices=new Notice();
        notices.setNews("是");
        notices.setUid(Util.getCurrentUser().getId());
        Notice notice =noticeMapper.show(notices);
        return  notice;
}


    public Notice sys(){
        Notice notices=new Notice();
        notices.setNews("是");
        Notice notice =noticeMapper.sys(notices);
        return  notice;
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
        return noticeMapper.getDataStatistics(Util.getCurrentUser().getId());
    }
}
