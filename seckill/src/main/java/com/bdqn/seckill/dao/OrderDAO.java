package com.bdqn.seckill.dao;

import com.bdqn.seckill.entity.Order;
import com.bdqn.seckill.entity.Order;

public interface OrderDAO {
    public void insert(Order order);

    public Order findByOrderNo(String orderNo);
}
