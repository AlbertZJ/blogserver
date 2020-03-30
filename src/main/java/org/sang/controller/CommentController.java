package org.sang.controller;

import org.sang.bean.*;
import org.sang.service.*;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 超级管理员专属Controller
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewComment(Comment comment) {

        int result = commentService.addNewComment(comment);
        if (result == 1) {
            return new RespBean("success", comment.getId() + "");
        } else {
            return new RespBean("error", comment.getState() == 0 ? "通知保存失败!" : "通知发表失败!");
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getCommentByState(@RequestParam(value = "state", defaultValue = "-1") Integer state, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords) {
        int totalCount = commentService.getCommentCountByState(state, Util.getCurrentUser().getId(),keywords);
        List<Comment> comment = commentService.getCommentByState(state, page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("comment", comment);
        return map;
    }

    @RequestMapping(value = "/{aid}", method = RequestMethod.GET)
    public Comment getCommentById(@PathVariable Long aid) {
        return commentService.getCommentById(aid);
    }

    @RequestMapping(value = "/com/{aid}", method = RequestMethod.GET)
    public List<Comment> getComById(@PathVariable Long aid) {
        return commentService.getComById(aid);
    }

    @RequestMapping(value = "/dustbin", method = RequestMethod.PUT)
    public RespBean updateCommentState(Long[] aids, Integer state) {
        if (commentService.updateCommentState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/restore", method = RequestMethod.PUT)
    public RespBean restoreComment(Integer commentId) {
        if (commentService.restoreComment(commentId) == 1) {
            return new RespBean("success", "还原成功!");
        }
        return new RespBean("error", "还原失败!");
    }

    @RequestMapping("/dataStatistics")
    public Map<String,Object> dataStatistics() {
        Map<String, Object> map = new HashMap<>();
        //  List<String> categories = noticesService.getCategories();
        List<Integer> dataStatistics = commentService.getDataStatistics();
        //  map.put("categories", categories);
        map.put("ds", dataStatistics);
        return map;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean add(Comment comment,Long aid,String content) {
        if ("".equals(comment.getContent()) || comment.getContent() == null) {
            return new RespBean("error", "请输入评论内容!");
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         int result = commentService.add(comment,aid,content,timestamp);
        if (result == 1) {
            Comment ls = commentService.ls(comment,timestamp);
            int coun = commentService.coun(ls);
            if(coun==1){
                return new RespBean("success", "添加成功!");
            }
            return new RespBean("error", "添加失败!");
        }
        return new RespBean("error", "添加失败!");
    }
    @RequestMapping(value = "/adds", method = RequestMethod.POST)
    public RespBean adds(Comment comment,Long aid,String content) {
        if ("".equals(comment.getContent()) || comment.getContent() == null) {
            return new RespBean("error", "请输入评论内容!");
        }
        int result = commentService.adds(comment,aid,content);
        if (result == 1) {
            return new RespBean("success", "添加成功!");
        }
        return new RespBean("error", "添加失败!");
    }
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteById(@PathVariable Long ids) {
        int result = commentService.deleteCommentByIds(ids);
        if (result==1) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
}
