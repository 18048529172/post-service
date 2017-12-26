package com.micro.api.post.exceptions;

import javax.annotation.Nullable;

/**
 * 说明：异常工具
 *
 * @author liw@suncd.com
 * @date 2017/12/25 11:32
 */
public class ExceptionChecks {

    /**
     *  参数验证异常
     * @param expression
     */
    public static void checkArgument(boolean expression, String message) {
        if (!expression) {
            throw new ArgumentCheckException(message);
        }
    }

    /**
     *  检查逻辑异常
     * @param expression
     */
    public static void checkLogic(boolean expression, String message) {
        if (!expression) {
            throw new LogicCheckException(message);
        }
    }

}
