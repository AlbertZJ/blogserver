package org.sang.controller;

import org.sang.bean.Article;
import org.sang.bean.Notice;
import org.sang.bean.RespBean;
import org.sang.service.NoticeService;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by albert on 2020/1/2.
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    NoticeService noticesService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewNotice(Notice notice) {
        int result = noticesService.addNewNotice(notice);
        if (result == 1) {
            return new RespBean("success", notice.getId() + "");
        } else {
            return new RespBean("error", notice.getState() == 0 ? "通知保存失败!" : "通知发表失败!");
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getNoticeByState(@RequestParam(value = "state", defaultValue = "-1") Integer state, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords) {
        int totalCount = noticesService.getNoticeCountByState(state, Util.getCurrentUser().getId(), keywords);
        List<Notice> notice = noticesService.getNoticeByState(state, page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("notice", notice);
        return map;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public Notice show() {
        return noticesService.show();
    }

    @RequestMapping(value = "/sys", method = RequestMethod.GET)
    public Notice sys() {
        return noticesService.sys();
    }

    @RequestMapping(value = "/{aid}", method = RequestMethod.GET)
    public Notice getNoticeById(@PathVariable Long aid) {
        return noticesService.getNoticeById(aid);
    }

    @RequestMapping(value = "/dustbin", method = RequestMethod.PUT)
    public RespBean updateNoticeState(Long[] aids, Integer state) {
        if (noticesService.updateNoticeState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/restore", method = RequestMethod.PUT)
    public RespBean restoreArticle(Integer noticeId) {
        if (noticesService.restoreNotice(noticeId) == 1) {
            return new RespBean("success", "还原成功!");
        }
        return new RespBean("error", "还原失败!");
    }

    @RequestMapping("/dataStatistics")
    public Map<String, Object> dataStatistics() {
        Map<String, Object> map = new HashMap<>();
        //  List<String> categories = noticesService.getCategories();
        List<Integer> dataStatistics = noticesService.getDataStatistics();
        //  map.put("categories", categories);
        map.put("ds", dataStatistics);
        return map;
    }
}
