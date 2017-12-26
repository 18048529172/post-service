package com.micro.api.post.dto;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/26 17:06
 */
public class UpdateThemeDTO implements Serializable{



    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
