package com.micro.api.post.exceptions;

/**
 * 说明：业务逻辑异常
 *
 * @author liw@suncd.com
 * @date 2017/12/25 11:37
 */
public class LogicCheckException extends AbsException {

    public LogicCheckException(String message) {
        super(ExceptionCodes.LOGINC_ERROR,message);
    }

}
