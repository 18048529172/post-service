package com.micro.api.post.dto;

import com.micro.api.post.core.dao.specification.PostSpecification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel
public class QueryPost implements Serializable {

    @ApiModelProperty(value = "父级id",required = false)
    private Long parentId;

    @ApiModelProperty(value = "名称，模糊查询",required = false)
    private String name;

    @ApiModelProperty(value = "开始时间",required = false)
    private Long beginTime;

    @ApiModelProperty(value = "结束时间",required = false)
    private Long endTime;

    @ApiModelProperty(value = "创建用户",required = false)
    private String createUserId;

    @ApiModelProperty(value = "组织机构，必须传入",required = true)
    private String orgId;

    @ApiModelProperty(value = "是否删除",required = true)
    private Boolean delete = false;

    @ApiModelProperty(value = "分页参数:{page:1,pageSize:20}",required = false,reference="Page")
    private Page page;

    @ApiModelProperty(value = "排序参数，如果为空默认时间倒序,[{property:\"createTime\",orderType:\"ASC\"}]",required = false)
    private List<OrderBy> orderBies = new ArrayList<>();

    @ApiModelProperty(value = "标签，可以传入多个[tagId1,tagId2,...]",required = false)
    private List<Long> tagIds = new ArrayList<>();


    public Long getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<OrderBy> getOrderBies() {
        return orderBies;
    }

    public void setOrderBies(List<OrderBy> orderBies) {
        this.orderBies = orderBies;
    }

    public Boolean getDelete() {
        if(this.delete == null){
            return false;
        }
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public PostSpecification toPostSpecification(){
        return new PostSpecification(this);
    }

    public PageRequest toPageAble(){
       List<Sort.Order> orders = new ArrayList<>();
       if(orderBies != null){
           for(OrderBy orderBy : orderBies){
               orders.add(new Sort.Order(Sort.Direction.fromString(orderBy.getOrderType()),orderBy.getProperty()));
           }
       }
       if(this.getPage() == null){
           this.page = new Page();
       }
       Sort sort = null;
       if(orders.isEmpty()){
           sort = new Sort(new Sort.Order(Sort.Direction.DESC,"createTime"));
       }else{
           sort = new Sort(orders);
       }
       return new PageRequest(this.getPage().getPage()-1,this.getPage().getPageSize(),sort);
    }


}
