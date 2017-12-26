package com.micro.api.post.core.service.impl;

import com.micro.api.post.core.dao.PostDAO;
import com.micro.api.post.core.dao.PostTagDAO;
import com.micro.api.post.core.dao.TagDAO;
import com.micro.api.post.core.dao.specification.PostSpecification;
import com.micro.api.post.core.entity.Post;
import com.micro.api.post.core.entity.PostTag;
import com.micro.api.post.core.entity.Tag;
import com.micro.api.post.core.service.PostService;
import com.micro.api.post.exceptions.ExceptionChecks;
import com.micro.api.post.dto.AddPost;
import com.micro.api.post.dto.UpdatePost;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;
    @Autowired
    private PostTagDAO postTagDAO;
    @Autowired
    private TagDAO tagDAO;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Long save(AddPost post) {
        this.saveCheck(post);
        Post postEntity = post.toPost(this);
        this.postDAO.save(postEntity);
        this.bindTags(postEntity,post);
        return postEntity.getId();
    }


    @Override
    public Post findById(Long id) {
        return this.postDAO.findOne(id);
    }

    @Override
    public Page<Post> page(PostSpecification postSpecification, PageRequest pageRequest) {
        return this.postDAO.findAll(postSpecification,pageRequest);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void delete(Long id) {
        postDAO.updateIsDelete(id,true);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void update(UpdatePost updatePost) {
        ExceptionChecks.checkArgument(updatePost.getId() != null,"id不能为空");
        if(StringUtils.isNotBlank(updatePost.getContent())){
            ExceptionChecks.checkArgument((updatePost.getContent().length()<=8000),"内容不能大于8000字符");
        }
        if(StringUtils.isNotBlank(updatePost.getMemo()) ){
            ExceptionChecks.checkArgument((updatePost.getContent().length()<=4000),"备注不能大于4000字符");
        }
        if(StringUtils.isNotBlank(updatePost.getName())){
            ExceptionChecks.checkArgument((updatePost.getName().length()<=200),"名称不能大于200字符");
        }
        Post post = this.postDAO.findOne(updatePost.getId());
        ExceptionChecks.checkArgument(post != null,"当前帖子不存在");
        updatePost.updateValue(post);
    }


    private void saveCheck(AddPost post) {
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(post.getName()), "名称不能为空");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(post.getOrgId()),"组织机构不能为空");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(post.getCreateUserId()),"创建用户不能为空");
        ExceptionChecks.checkArgument((post.getName().length()<=200),"名称不能超过200个字符");
        if(StringUtils.isNotBlank(post.getContent())){
            ExceptionChecks.checkArgument((post.getContent().length()<=8000),"内容不能大于8000字符");
        }
        if(StringUtils.isNotBlank(post.getMemo()) ){
            ExceptionChecks.checkArgument((post.getContent().length()<=4000),"备注不能大于4000字符");
        }
        Long parentId = post.getParentId();
        if(parentId != null){
            Post parentPost = this.postDAO.findOne(parentId);
            ExceptionChecks.checkArgument(parentPost != null, "父节点不存在");
        }
    }


    private void bindTags(Post postEntity, AddPost post) {
        List<Long> tagIds = post.getTagIds();
        if(tagIds != null){
            List<PostTag> postTags = new ArrayList<>();
            for(Long tagId : tagIds){
                Tag tag = this.tagDAO.findOne(tagId);
                if(tag == null){
                    continue;
                }
                PostTag postTag = new PostTag();
                postTag.setPost(postEntity);
                postTag.setTag(tag);
                postTags.add(postTag);
            }
            this.postTagDAO.save(postTags);
        }
    }


}
