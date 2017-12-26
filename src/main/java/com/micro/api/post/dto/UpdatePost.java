package com.micro.api.post.dto;

import com.micro.api.post.core.entity.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import java.io.Serializable;

@ApiModel
public class UpdatePost implements Serializable {

    @ApiModelProperty(value = "id",required = true)
    private Long id;

    @ApiModelProperty(value = "名称，长度最长为200",required = false,position = 200)
    @Column(length = 200)
    private String name;

    @ApiModelProperty(value = "内容，长度最长为8000",required = false,position = 8000)
    @Column(length = 8000)
    private String content;

    @ApiModelProperty(value = "备注，长度最长为4000",required = false,position = 8000)
    @Column(length = 4000)
    private String memo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void updateValue(Post post){
        if(StringUtils.isNotBlank(this.getName())){
            post.setName(this.getName());
        }
        if(StringUtils.isNotBlank(this.getContent())){
            post.setContent(this.getContent());
        }
        if(StringUtils.isNotBlank(this.getMemo())){
            post.setMemo(this.getMemo());
        }
    }

}
