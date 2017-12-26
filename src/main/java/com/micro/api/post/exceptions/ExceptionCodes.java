package com.micro.api.post.exceptions;

/**
 * 说明：错误code和message定义
 *
 * @author liw@suncd.com
 * @date 2017/12/25 11:20
 */
public enum ExceptionCodes {

    ARGUMENT_ERROR("参数异常","-1"),
    LOGINC_ERROR("逻辑异常","-2"),
    ERROR("错误","-3")
    ;

    private ExceptionCodes(String name,String code){
        this.name = name;
        this.code = code;
    }
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
