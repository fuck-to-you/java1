package com.bdqn.seckill.controller;

import com.bdqn.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author ldwjava
 * @Date 2019/7/10 9:43
 * @Desc TODO
 **/

@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/qiangou")
    @ResponseBody
    public String secskill() {
        goodsService.qiangou();
        return "qianggouchenggong";
    }


}
