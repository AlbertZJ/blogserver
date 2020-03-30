package org.sang.service;

import org.sang.bean.*;
import org.sang.mapper.*;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
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
    @Autowired
    ComLikesMapper comLikesMapper;

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
        List<Comment> so=new ArrayList<Comment>();
        List<Comment> co=commentMapper.getComById(aid);
       // List<Comment> ao=commentMapper.getCom(aid);

            for (Comment one : co) {
//                if (one.getParentId() == -1) {
                      so.add(one);
//                }
               // if (one.getParentId() != -1) {
                    List<Comment> comments = commentMapper.getOne(one.getId());
                    for (Comment re : comments) {
                        re.setComname(re.getComname()+"  回复  "+one.getComname()+":");
                        so.add(re);
                    }
               // }
            }

//        Iterator<Comment> it=co.iterator();
//        while (it.hasNext()){
//
//            Comment one=it.next();
//
//        }
        return so;
    }

//    public Comment getComById(Long aid) {
//        Comment comment = commentMapper.getComById(aid);
//        // NoticeMapper.pvIncrement(aid);
//        return comment;
//    }



    /**
     * 获取最近七天的数据
     * @return
     */
    public List<Integer> getDataStatistics() {
        return commentMapper.getDataStatistics(Util.getCurrentUser().getId());
    }


    public int add(Comment comment,Long aid,String content,Timestamp timestamp) {
        //添加操作
       // Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comment.setPublishDate(timestamp);
        int result=commentMapper.add(comment);
//        if(result==1){
////            Comment co=new Comment();
////            co.setPublishDate(timestamp);
////            //设置当前用户(通过当前用户id)
////            co.setUid(Util.getCurrentUser().getId());
//            Comment cids=new Comment();
//            cids= commentMapper.selectcid(comment);
//
//                Countcomlikes countcomlikes=new Countcomlikes();
//                countcomlikes.setCid(cids.getId());
//                countcomlikes.setPublishDate(new Timestamp(System.currentTimeMillis()));
//                countcomlikesMapper.add(countcomlikes);
//
//        }

        return result;
    }

    public Comment ls(Comment comment,Timestamp timestamp) {
        comment.setPublishDate(timestamp);
        Comment cids=new Comment();
        cids= commentMapper.selectcid(comment);

        return cids;
    }

    public int coun(Comment cids) {
        Countcomlikes countcomlikes=new Countcomlikes();
        countcomlikes.setCid(cids.getId());
        countcomlikes.setPublishDate(new Timestamp(System.currentTimeMillis()));
        int result=countcomlikesMapper.add(countcomlikes);
        return result;
    }

    public int adds(Comment comment,Long aid,String content) {
        //添加操作
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comment.setPublishDate(timestamp);
        comment.setUid(Util.getCurrentUser().getId());
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


    public int deleteCommentByIds(Long id) {
        Comment comment=new Comment();
        comment.setId(id);
        int result = commentMapper.updateCommentByIds(comment);
        return result;
    }
}
