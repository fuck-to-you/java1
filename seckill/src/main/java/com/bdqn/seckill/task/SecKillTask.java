package com.bdqn.seckill.task;

import com.bdqn.seckill.entity.PromotionSecKill;
import com.bdqn.seckill.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author ldwjava
 * @Date 2019/7/10 10:16
 * @Desc TODO
 **/

@Component
public class SecKillTask {

    @Autowired
    private SecKillService secKillService;

    @Autowired
    private RedisTemplate redisTemplate;


    @Scheduled(cron = "0/5 * * * * ?")
    public void startSecKill() {
        //查询秒杀表有没有满足条件的需要启动的秒杀活动
        List<PromotionSecKill> list = secKillService.findUnstartSkill();
        for (PromotionSecKill promotionSecKill : list) {
            for (int i = 1; i <= promotionSecKill.getPsCount(); i++) {
                redisTemplate.opsForList().leftPush("seckill:count:" + promotionSecKill.getPsId(), promotionSecKill.getGoodsId());
            }
            promotionSecKill.setStatus(1);
            secKillService.updateStatus(promotionSecKill);
        }
    }
}
