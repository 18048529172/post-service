package com.micro.api.post.utils.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Meta {

    @ApiModelProperty(value = "返回码，200:成功,其他：失败",notes = "200:成功,其他：失败")
    private String code;
    @ApiModelProperty(value = "返回信息，success：成功，其他：表示其他信息",notes = "success：成功，其他：表示其他信息")
    private String message;
    @ApiModelProperty(value = "请求时间，单位（毫秒）")
    private Long requestTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }
}
