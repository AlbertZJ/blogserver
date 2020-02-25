package org.sang.service;

import org.sang.bean.Comment;
import org.sang.bean.CommentUser;
import org.sang.bean.Countcomlikes;
import org.sang.bean.Notice;
import org.sang.mapper.CommentMapper;
import org.sang.mapper.CountcomlikesMapper;
import org.sang.mapper.NoticeMapper;
import org.sang.mapper.TagsMapper;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by albert on 2019/12/19.
 */
@Service
@Transactional
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CountcomlikesMapper  countcomlikesMapper;

    public int addNewComment(Comment comment) {
        if (comment.getId() == -1) {
            //添加操作
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (comment.getState() == 1) {
                //设置发表日期
                comment.setPublishDate(timestamp);
            }
            //   notice.setEditTime(timestamp);

            //设置当前用户(通过当前用户id)
            comment.setUid(Util.getCurrentUser().getId());
            int i = commentMapper.addNewComment(comment);
            return i;
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (comment.getState() == 1) {
                //设置发表日期
                comment.setPublishDate(timestamp);
            }
            //更新
            // notice.setEditTime(new Timestamp(System.currentTimeMillis()));
            int i = commentMapper.updateComment(comment);
            return i;
        }
    }



    public List<Comment> getCommentByState(Integer state, Integer page, Integer count,String keywords) {
        int start = (page - 1) * count;
        Long uid = Util.getCurrentUser().getId();
        return commentMapper.getCommentByState(state, start, count, uid,keywords);
    }

    public List<Comment> getComByState(Integer state, Integer page, Integer count) {
        int start = (page - 1) * count;
        Long uid = Util.getCurrentUser().getId();
        return commentMapper.getComByState(state, start, count, uid);
    }



//    public List<Article> getArticleByStateByAdmin(Integer page, Integer count,String keywords) {
//        int start = (page - 1) * count;
//        return articleMapper.getArticleByStateByAdmin(start, count,keywords);
//    }

    public int getCommentCountByState(Integer state, Long uid,String keywords) {
        return commentMapper.getCommentCountByState(state, uid,keywords);
    }

    public int getComCountByState(Integer state, Long uid) {
        return commentMapper.getComCountByState(state, uid);
    }

    public int updateCommentState(Long[] aids, Integer state) {
        if (state == 2) {
            return commentMapper.deleteCommentById(aids);
        } else {
            return commentMapper.updateCommentState(aids, 2);//放入到回收站中
        }
    }

    public int restoreComment(Integer commentId) {
        return commentMapper.updateCommentStateById(commentId, 1); // 从回收站还原在原处
    }

    public Comment getCommentById(Long aid) {
        Comment comment = commentMapper.getCommentById(aid);
        // NoticeMapper.pvIncrement(aid);
        return comment;
    }

    public List<Comment> getComById(Long aid) {

        return commentMapper.getComById(aid);
    }

//    public Comment getComById(Long aid) {
//        Comment comment = commentMapper.getComById(aid);
//        // NoticeMapper.pvIncrement(aid);
//        return comment;
//    }

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
        return commentMapper.getDataStatistics(Util.getCurrentUser().getId());
    }

//    public int addComment(Comment comment,Long aid,String content) {
//        //添加操作
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//
//        comment.setUid(Util.getCurrentUser().getId());
//        comment.setParentId(aid);
//        int result=commentMapper.addComment(comment);
//        Comment co=new Comment();
//        co.setPublishDate(timestamp);
//        //设置当前用户(通过当前用户id)
//        co.setUid(Util.getCurrentUser().getId());
//        Comment cid=new Comment();
//        cid= commentMapper.selectcid(co);
//        Countcomlikes countcomlikes=new Countcomlikes();
//        countcomlikes.setCid(cid.getId());
//        countcomlikes.setPublishDate(new Timestamp(System.currentTimeMillis()));
//        countcomlikesMapper.add(countcomlikes);
//
//        return result;
//    }

    public int add(Comment comment,Long aid,String content) {
        //添加操作
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comment.setPublishDate(timestamp);
        int result=commentMapper.add(comment);
        Comment co=new Comment();
        co.setPublishDate(timestamp);
        //设置当前用户(通过当前用户id)
        co.setUid(Util.getCurrentUser().getId());
        Comment cid=new Comment();
          cid= commentMapper.selectcid(co);
        Countcomlikes countcomlikes=new Countcomlikes();
 countcomlikes.setCid(cid.getId());
       countcomlikes.setPublishDate(new Timestamp(System.currentTimeMillis()));
       countcomlikesMapper.add(countcomlikes);

        return result;
    }
}
//    public List<CommentUser> findAll() {
//        return commentMapper.findAll();
//    }
//
//    public boolean deleteByIds(String ids) {
//        String[] split = ids.split(",");
//        int result = commentMapper.deleteByIds(split);
//        return result == split.length;
//    }
//
//    public int updateById(Comment comment) {
//        return commentMapper.updateById(comment);
//    }
//

//}
