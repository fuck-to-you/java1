package com.bdqn.seckill.dao;

import org.springframework.stereotype.Component;

/**
 * @Author ldwjava
 * @Date 2019/7/10 9:39
 * @Desc TODO
 **/
@Component
public class GoodsDao {
    static int count = 10;

    //获取数量
    public int getCount() {
        return count;
    }


    public void update(int count) {
        this.count = count;
    }


}
