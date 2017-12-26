package com.micro.api.post.exceptions;

/**
 * 说明：自定义异常父类
 *
 * @author liw@suncd.com
 * @date 2017/12/25 11:19
 */
public abstract class AbsException extends RuntimeException{

    private ExceptionCodes exceptionCodes;

    public AbsException( ExceptionCodes exceptionCodes,String message ){
        super(message);
        this.exceptionCodes = exceptionCodes;
    }

    public ExceptionCodes getExceptionCodes() {
        return exceptionCodes;
    }

}
