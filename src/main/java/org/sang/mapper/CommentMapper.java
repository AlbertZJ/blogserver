package org.sang.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.Comment;
import org.sang.bean.CommentUser;
import org.sang.bean.Notice;


import java.util.List;

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

    Comment getCommentById(Long aid);
    Comment selectcid(Comment comment);

    List<Comment> getComById(Long aid);

    List<Integer> getDataStatistics(Long uid);

    int add(Comment comment);

}
    // @Insert("insert into comments(aid,content,publishDate,parentId,uid) values(#{aid},#{content},#{publishDate},#{parentId},#{uid})")
//    int add(Comment comment);
//
//   // @Delete("delete from comments where id=1")
//    int deleteByIds(@Param("ids") String[] ids);
//
//    // @Select("SELECT c.id,a.title,c.content,c.publishDate,c.parentId,u.nickname FROM comments AS c,article AS a,USER AS u WHERE a.id=c.aid AND u.id=c.uid")
//    List<CommentUser> findAll();
//
//    // @Update("update comment set content=#{content},publishDate=#{publishDate},parentId=#{parentId}")
//    int updateById(Comment comment);
//}
