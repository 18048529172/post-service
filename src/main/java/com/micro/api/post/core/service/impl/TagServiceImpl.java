package com.micro.api.post.core.service.impl;

import com.micro.api.post.core.dao.TagDAO;
import com.micro.api.post.core.entity.Tag;
import com.micro.api.post.core.service.TagService;
import com.micro.api.post.exceptions.ExceptionChecks;
import com.micro.api.post.dto.AddTag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/25 15:48
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDAO tagDAO;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Long add(AddTag addTag) {
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(addTag.getOrgId()),"组织机构不能为空");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(addTag.getName()),"标签名称不能为空");
        ExceptionChecks.checkArgument((addTag.getName().length()<=20),"标签名称长度不能超过20个字符");
        long countRows = this.tagDAO.countByOrgIdAndName(addTag.getOrgId(),addTag.getName());
        ExceptionChecks.checkLogic((countRows == 0),"名称不能重复");
        Tag tag = new Tag();
        tag.setName(addTag.getName());
        tag.setOrgId(addTag.getOrgId());
        this.tagDAO.save(tag);
        return tag.getId();
    }

    @Override
    public List<Tag> findByOrgIdAndIsDeleteFase(String orgId ) {
        return tagDAO.findByOrgIdAndIsDeleteFalseOrderByCreateTimeDesc(orgId);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void deleteById(Long id) {
        this.tagDAO.updateDeleteStatus(id,true);
    }
}
