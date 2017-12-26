package com.micro.api.post.dto;

import com.micro.api.post.core.entity.Theme;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/26 13:54
 */
@ApiModel
public class AddThemeDTO implements Serializable {

    @ApiModelProperty(value = "名称，必传，不能重复,长度不能超过200",required = true)
    private String name;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id，必传",required = true)
    private String userId;
    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id，必传",required = true)
    private String tenementId;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id，不必传",required = false)
    private Long parentId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注，不必传,长度：4000",required = false)
    private String memo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTenementId() {
        return tenementId;
    }

    public void setTenementId(String tenementId) {
        this.tenementId = tenementId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Theme toEntity( AddThemeDTO addThemeDTO ) {
        Theme theme = new Theme();
        theme.setDelete(false);
        theme.setMemo(addThemeDTO.getMemo());
        theme.setName(addThemeDTO.getName());
        theme.setTenementId(addThemeDTO.getTenementId());
        theme.setUserId(addThemeDTO.getUserId());
        return theme;
    }
}
