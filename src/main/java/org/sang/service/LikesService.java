package org.sang.service;

import org.sang.bean.Countlikes;
import org.sang.bean.Likes;
import org.sang.mapper.CountlikesMapper;
import org.sang.mapper.LikesMapper;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by albert on 2019/12/19.
 */
@Service
@Transactional
public class LikesService {
    @Autowired
    LikesMapper likesMapper;
    @Autowired
    CountlikesMapper countlikesMapper;

    public Countlikes select(Countlikes countlikes){
        return countlikesMapper.selects(countlikes);
    }

    public int addCount(Countlikes countlikes){
        countlikes.setPublishDate(new Timestamp(System.currentTimeMillis()));
        return countlikesMapper.add(countlikes);
    }

    public int deleteCount(Countlikes countlikes){
        return countlikesMapper.delete(countlikes);
    }

    public int update(Countlikes countlikes){
        return countlikesMapper.update(countlikes);
    }
//    public Comment getCommentById(Long aid) {
//        Comment comment = commentMapper.getCommentById(aid);
//        // NoticeMapper.pvIncrement(aid);
//        return comment;
//    }
    public int add(Long aid) {
        Countlikes countlikes=new Countlikes();
        countlikes.setAid(aid);
        countlikes.setPublishDate(new Timestamp(System.currentTimeMillis()));
        int add=countlikesMapper.add(countlikes);
        countlikesMapper.update(countlikes);

        Likes likes=new Likes();
        likes.setAid(aid);
        likes.setLikes(1);
        //设置当前用户(通过当前用户id)
        likes.setUid(Util.getCurrentUser().getId());
        likes.setPublishDate(new Timestamp(System.currentTimeMillis()));
        return likesMapper.add(likes);
    }
    public int adddislike(Long aid) {
        Countlikes countlikes=new Countlikes();
        countlikes.setAid(aid);
        countlikes.setPublishDate(new Timestamp(System.currentTimeMillis()));
        int add=countlikesMapper.add(countlikes);
        countlikesMapper.updatedislike(countlikes);

        Likes likes=new Likes();
        likes.setAid(aid);
        likes.setLikes(-1);
        //设置当前用户(通过当前用户id)
        likes.setUid(Util.getCurrentUser().getId());
        likes.setPublishDate(new Timestamp(System.currentTimeMillis()));
        return likesMapper.add(likes);
    }
//    public int add(Likes likes) {
//        //设置当前用户(通过当前用户id)
//        likes.setUid(Util.getCurrentUser().getId());
//        likes.setPublishDate(new Timestamp(System.currentTimeMillis()));
//        return likesMapper.add(likes);
//    }
    public Likes getLikes(Long aid){
        Likes likes=new Likes();
        likes.setAid(aid);
        //设置当前用户(通过当前用户id)
        likes.setUid(Util.getCurrentUser().getId());
     return likesMapper.getLikes(likes);
    }
    public int deletelike(Long aid,int likestate){
        Countlikes countlikes=new Countlikes();
        countlikes.setAid(aid);
        countlikes.setPublishDate(new Timestamp(System.currentTimeMillis()));
         int add=countlikesMapper.add(countlikes);
        if(likestate==-1){
            countlikesMapper.deletedislike(countlikes);
        }else{
            countlikesMapper.updatedelete(countlikes);
        }

        Likes likes=new Likes();
        likes.setAid(aid);
        likes.setLikes(likestate);
        //设置当前用户(通过当前用户id)
        likes.setUid(Util.getCurrentUser().getId());
        return likesMapper.deletelike(likes);
    }

//    public int deletelike(Likes likes){
//        //设置当前用户(通过当前用户id)
//        likes.setUid(Util.getCurrentUser().getId());
//        return likesMapper.deletelike(likes);
//    }

}
