package com.bdqn.seckill.controller;

import com.bdqn.seckill.entity.Order;
import com.bdqn.seckill.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ldwjava
 * @Date 2019/7/10 10:20
 * @Desc TODO
 **/

@Controller
public class SecKillController {

    @Autowired
    SecKillService secKillService;


    @RequestMapping("/seckill")
    @ResponseBody
    public Map seckill(Integer psid, Integer pid, String uid) {
        Map result = new HashMap();
        try {
            secKillService.qinagou(psid, pid, uid);

            //发送订单到mq
            System.out.println("抢购成功----发送相关信息到mq,让下游的服务消费创建订单");

            String order_no = secKillService.sendMsgToMq(uid);

            //需要订单号,返回到页面 供页面做查询
            result.put("code", 0);
            result.put("orderNo", order_no);
            result.put("msg", "恭喜你抢购成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 1);
            result.put("msg", e.getMessage());
        }
        return result;
    }


    /* 检查订单是否创建成功*/
    @RequestMapping("/checkOrder")
    public ModelAndView checkOut(String orderNo) {
        System.out.println("test");
        Order order = secKillService.findOrderByOrderNO(orderNo);
        ModelAndView modelAndView = new ModelAndView();
        if (order == null) {  //消费者还没消费到该订单
            modelAndView.setViewName("waiting");
            modelAndView.addObject("orderNo", orderNo);
        } else {
            modelAndView.addObject("order", order);
            modelAndView.setViewName("order");
        }
        return modelAndView;
    }


    @RequestMapping("/to")
    public String to(){
        System.out.println("order");
        return "order";
    }

}
