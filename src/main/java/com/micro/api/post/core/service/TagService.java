package com.micro.api.post.core.service;

import com.micro.api.post.core.entity.Tag;
import com.micro.api.post.dto.AddTag;

import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/25 15:43
 */
public interface TagService {

    Long add(AddTag addTag);

    List<Tag> findByOrgIdAndIsDeleteFase(String orgId );

    void deleteById(Long id);

}
