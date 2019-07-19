package com.bdqn.seckill.exception;

/**
 * @Author ldwjava
 * @Date 2019/7/10 11:49
 * @Desc TODO
 **/

public class SecKillExcpetion extends RuntimeException {


    public SecKillExcpetion() {
        super();
    }

    public SecKillExcpetion(String message) {
        super(message);
    }

    public SecKillExcpetion(String message, Throwable cause) {
        super(message, cause);
    }
}
