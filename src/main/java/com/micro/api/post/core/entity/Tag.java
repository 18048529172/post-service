package com.micro.api.post.core.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/25 14:57
 */
@Entity
@Table(name = "micro_tag")
public class Tag implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String orgId;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "post")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<PostTag> postTags = new HashSet<>();

    private Boolean isDelete = false;

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Set<PostTag> getPostTags() {
        return postTags;
    }

    public void setPostTags(Set<PostTag> postTags) {
        this.postTags = postTags;
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
}
