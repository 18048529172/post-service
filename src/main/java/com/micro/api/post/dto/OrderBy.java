package com.micro.api.post.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderBy {

    @ApiModelProperty(value = "排序字段")
    private String property;
    @ApiModelProperty(value = "排序方式，ASC:顺序，DESC:倒序")
    private String orderType;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
