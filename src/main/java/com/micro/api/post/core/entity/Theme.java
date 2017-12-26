package com.micro.api.post.core.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/26 10:17
 */
@Entity
@Table(name = "micro_theme")
public class Theme implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    /**
     * 名称
     */
    @Column(name = "name",length = 200)
    private String name;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 冗余
     */
    private String userName;
    /**
     * 租户id
     */
    private String tenementId;

    /**
     * 是否删除
     */
    private Boolean isDelete = false;

    /**
     * 父级对象
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Theme parent;
    /**
     * 备注
     */
    @Column(name = "memo",length = 4000)
    private String memo;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();


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

    public Theme getParent() {
        return parent;
    }

    public void setParent(Theme parent) {
        this.parent = parent;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
