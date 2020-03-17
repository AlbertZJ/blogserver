package org.sang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by albert on 2017/12/25.
 */
@Component
public class DataStatisticsComponent {
    @Autowired
    ArticleService articleService;

    //每天执行一次，统计PV(定时任务)
//    @Scheduled(cron = "1 0 0 * * ?")
   // @Scheduled(cron = "0 0/1 * * * ?")
    @Scheduled(cron = "1 0 0 * * ?")
    public void pvStatisticsPerDay() {
        articleService.pvStatisticsPerDay();
    }
}
