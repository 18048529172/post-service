package com.micro.api.post.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/25 15:46
 */
@ApiModel
public class AddTag  implements Serializable{

    @ApiModelProperty(value = "标签名称，名称不能重复，长度最大为20个字符")
    private String name;

    @ApiModelProperty(value = "组织机构，不能为空")
    private String orgId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

}
