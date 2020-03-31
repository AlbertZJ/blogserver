package org.sang.controller.admin;

import org.sang.bean.Article;
import org.sang.bean.Comment;
import org.sang.bean.Notice;
import org.sang.bean.RespBean;
import org.sang.service.CommentService;
import org.sang.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 超级管理员专属Controller
 */
@RestController
@RequestMapping("/admin")
public class CommentsController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comment/all", method = RequestMethod.GET)
    public Map<String, Object> getCommentByStateByAdmin(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords) {
        List<Comment> comment = commentService.getCommentByState(-2, page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("comment", comment);
        map.put("totalCount", commentService.getCommentCountByState(1, null, keywords));
        return map;
    }

    @RequestMapping(value = "/comment/dustbin", method = RequestMethod.PUT)
    public RespBean updateCommentState(Long[] aids, Integer state) {
        if (commentService.updateCommentState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
}
