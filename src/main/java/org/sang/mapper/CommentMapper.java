package org.sang.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.Comment;
import org.sang.bean.CommentUser;
import org.sang.bean.Notice;


import java.util.List;

/**
 * Created by albert on 2019/12/19.
 */
@Mapper
public interface CommentMapper {

    int addNewComment(Comment comment);

    int updateComment(Comment comment);

    List<Comment> getCommentByState(@Param("state") Integer state, @Param("start") Integer start, @Param("count") Integer count, @Param("uid") Long uid, @Param("keywords") String keywords);

    List<Comment> getComByState(@Param("state") Integer state, @Param("start") Integer start, @Param("count") Integer count, @Param("uid") Long uid);

//    List<Article> getArticleByStateByAdmin(@Param("start") int start, @Param("count") Integer count, @Param("keywords") String keywords);

    int getCommentCountByState(@Param("state") Integer state, @Param("uid") Long uid, @Param("keywords") String keywords);

    int getComCountByState(@Param("state") Integer state, @Param("uid") Long uid);

    int updateCommentState(@Param("aids") Long aids[], @Param("state") Integer state);

    int updateCommentStateById(@Param("commentId") Integer commentId, @Param("state") Integer state);

    int deleteCommentById(@Param("aids") Long[] aids);

    int updateCommentByIds(Comment comment);

    Comment getCommentById(Long aid);

    Comment selectcid(Comment comment);

    List<Comment> getComById(Long aid);

    List<Comment> getCom(Long aid);

    List<Comment> getOne(Long parentId);

    List<Integer> getDataStatistics(Long uid);

    int add(Comment comment);

    int deleteCommentByIds(Long id);
}
