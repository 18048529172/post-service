package com.micro.api.post.core.dao.specification;

import com.micro.api.post.core.entity.PostTag;
import com.micro.api.post.core.entity.Tag;
import com.micro.api.post.dto.QueryPost;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostSpecification<Post> implements Specification<Post> {

    private QueryPost queryPost;

    public PostSpecification(QueryPost queryPost){
        this.queryPost = queryPost;
    }


    @Override
    public Predicate toPredicate(Root<Post> root,
                                 CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {
        if(this.queryPost != null){
            List<Predicate> predicates = new ArrayList<>();
            String name = this.queryPost.getName();
            if(StringUtils.isNotBlank(name)){
                Predicate namePredicate = criteriaBuilder.like(root.get("name"),"%"+this.queryPost.getName()+"%");
                predicates.add(namePredicate);
            }
            Long parentId = this.queryPost.getParentId();
            if(parentId != null){
                Predicate parentIdPredicate = criteriaBuilder.equal(root.get("parent").get("id"),parentId);
                predicates.add(parentIdPredicate);
            }
            Long beginTime = this.queryPost.getBeginTime();
            Long endTime = this.queryPost.getEndTime();
            if(beginTime != null && endTime != null){
                Date beginDate = new Date(beginTime);
                Date endDate = new Date(endTime);
                Predicate betweenPredicate = criteriaBuilder.between(root.get("createTime"),beginDate,endDate);
                predicates.add(betweenPredicate);
            }
            String createUserId = this.queryPost.getCreateUserId();
            if(StringUtils.isNotBlank(createUserId)){
                Predicate createUserIdPredicate = criteriaBuilder.equal(root.get("createUserId"),createUserId);
                predicates.add(createUserIdPredicate);
            }
            String orgId = this.queryPost.getOrgId();
            if(StringUtils.isNotBlank(orgId)){
                Predicate orgIdPredicate = criteriaBuilder.equal(root.get("orgId"),orgId);
                predicates.add(orgIdPredicate);
            }
            if(queryPost.getTagIds() != null && ! queryPost.getTagIds().isEmpty()){
                SetJoin<Post,PostTag> postTagSetJoin = root.joinSet("postTags", JoinType.LEFT);
                Join<PostTag,Tag> tagJoin =  postTagSetJoin.join("tag",JoinType.LEFT);
                CriteriaBuilder.In<Long> in = criteriaBuilder.in(tagJoin.get("id"));
                for(Long tagId : queryPost.getTagIds()){
                    in.value(tagId);
                }
                predicates.add(in);
            }
            Predicate deletePredicate = criteriaBuilder.equal(root.get("isDelete"),this.queryPost.getDelete());
            predicates.add(deletePredicate);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        }
        return null;
    }
}
