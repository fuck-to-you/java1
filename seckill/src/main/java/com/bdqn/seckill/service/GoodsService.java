package com.bdqn.seckill.service;

import com.bdqn.seckill.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ldwjava
 * @Date 2019/7/10 9:41
 * @Desc TODO
 **/

@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public void qiangou() {
        int count = goodsDao.getCount();
        if (count > 0) {
            int res = count - 1;
            System.out.println("抢购成功");
            ///3 s
            goodsDao.update(res);
        } else {
            System.out.println("商品抢购完了");
        }
    }
}
