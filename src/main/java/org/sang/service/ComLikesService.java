package org.sang.service;

import org.sang.bean.ComLikes;
import org.sang.bean.Countcomlikes;
import org.sang.bean.Countlikes;
import org.sang.bean.Likes;
import org.sang.mapper.ComLikesMapper;
import org.sang.mapper.CountcomlikesMapper;
import org.sang.mapper.LikesMapper;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by albert on 2019/12/19
 */
@Service
@Transactional
public class ComLikesService {
    @Autowired
    ComLikesMapper comlikesMapper;
    @Autowired
    CountcomlikesMapper countcomlikesMapper;

    public int add(Long cid,int likestate) {
        Countcomlikes countcomlikes=new Countcomlikes();
        countcomlikes.setCid(cid);
        countcomlikes.setLikes(likestate);
        countcomlikes.setPublishDate(new Timestamp(System.currentTimeMillis()));
        int add=countcomlikesMapper.add(countcomlikes);

        ComLikes num=new ComLikes();
        num.setCid(cid);
        num.setLikes(likestate);
        ComLikes coms=new ComLikes();
       coms=  comlikesMapper.number(num);

        if(likestate==-1){
            countcomlikes.setCountdislike(coms.countcomlikes+1);

            countcomlikesMapper.updatedislike(countcomlikes);
        }else{
            countcomlikes.setCountlike(coms.countcomlikes+1);

            countcomlikesMapper.update(countcomlikes);
        }

        ComLikes likes=new ComLikes();
        likes.setCid(cid);
        likes.setLikes(likestate);
        //设置当前用户(通过当前用户id)
        likes.setUid(Util.getCurrentUser().getId());
        likes.setPublishDate(new Timestamp(System.currentTimeMillis()));
        return comlikesMapper.add(likes);
    }



    public ComLikes getLikes(ComLikes likes){
        //设置当前用户(通过当前用户id)
        likes.setUid(Util.getCurrentUser().getId());
        return comlikesMapper.getLikes(likes);
    }
    public int deletelike(Long cid,int likestate){
        Countcomlikes countcomlikes=new Countcomlikes();
        countcomlikes.setCid(cid);
        countcomlikes.setPublishDate(new Timestamp(System.currentTimeMillis()));
        int add=countcomlikesMapper.add(countcomlikes);
        ComLikes num=new ComLikes();
        num.setCid(cid);
        num.setLikes(likestate);
        ComLikes coms=new ComLikes();
        coms=  comlikesMapper.number(num);



        if(likestate==-1){
//            if(coms.countcomlikes.equals(0)){
//                countcomlikes.setCountdislike(Integer.toUnsignedLong(0));
//            }else {
                countcomlikes.setCountdislike(coms.countcomlikes-1);
            //}
            countcomlikesMapper.deletedislike(countcomlikes);
        }else{

//            if(coms.countcomlikes.equals(0)){
//                countcomlikes.setCountlike(Integer.toUnsignedLong(0));
//            }else {
                countcomlikes.setCountlike(coms.countcomlikes-1);
            //}
            countcomlikesMapper.update(countcomlikes);
        }
        ComLikes likes=new ComLikes();
        likes.setCid(cid);
        likes.setLikes(likestate);
        //设置当前用户(通过当前用户id)
        likes.setUid(Util.getCurrentUser().getId());
        int l= comlikesMapper.deletelike(likes);

        return l;
    }


}
