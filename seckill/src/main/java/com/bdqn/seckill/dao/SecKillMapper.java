package com.bdqn.seckill.dao;

import com.bdqn.seckill.entity.PromotionSecKill;

import java.util.List;

/**
 * @Author ldwjava
 * @Date 2019/7/10 10:20
 * @Desc TODO
 **/

public interface SecKillMapper {

    List<PromotionSecKill> findUnstartSecKill();

    void updateStatus(PromotionSecKill promotionSecKill);
}
