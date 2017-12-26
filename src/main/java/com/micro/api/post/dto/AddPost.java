package com.micro.api.post.dto;

import com.google.common.base.Preconditions;
import com.micro.api.post.core.entity.Post;
import com.micro.api.post.core.service.PostService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "前端传入新增时的对象")
public class AddPost {

    @ApiModelProperty(value = "组织机构id",required = true)
    private String orgId;

    @ApiModelProperty(value = "用户id",required = true)
    private String createUserId;

    @ApiModelProperty(value = "名称，长度最长为200",required = true,position = 200)
    @Column(length = 200)
    private String name;

    @ApiModelProperty(value = "上级id",required = false,dataType = "long")
    private Long parentId;

    @ApiModelProperty(value = "标签id列表，例如：[1,2,3,...]",required = false)
    private List<Long> tagIds = new ArrayList<>();

    @ApiModelProperty(value = "内容，长度最长为8000",required = false,position = 8000)
    @Column(length = 8000)
    private String content;

    @ApiModelProperty(value = "备注，长度最长为4000",required = false,position = 8000)
    @Column(length = 4000)
    private String memo;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public Post toPost(PostService postService){
        //基本验证
        Preconditions.checkArgument(StringUtils.isNotBlank(this.getName()),"名称不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(this.getOrgId()),"组织机构不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(this.getCreateUserId()),"创建人不能为空");
        //验证长度
        Preconditions.checkArgument(this.getName().length()<=200,"名称不能超过200");
        String memo = this.getMemo();
        if(StringUtils.isNotBlank(memo)){
            Preconditions.checkArgument(memo.length()<= 4000,"备注不能超过4000");
        }
        String content = this.getContent();
        if(StringUtils.isNotBlank(content)){
            Preconditions.checkArgument(content.length()<= 8000,"备注不能超过8000");
        }
        Long parentId = this.getParentId();
        Post parent = null;
        if(parentId != null){
            parent = postService.findById(parentId);
            //验证上级是否存在
            Preconditions.checkArgument(parent != null,"父级id："+parentId+"，不存在");
        }
        //验证组织机构是否存在
        //验证用户是否存在
        //TODO
        Post post = new Post();
        post.setContent(this.getContent());
        post.setCreateTime(new Date());
        post.setMemo(memo);
        post.setName(this.getName());
        post.setOrgId(this.getOrgId());
        post.setCreateUserId(this.getCreateUserId());
        post.setParent(parent);
        return post;
    }

}
