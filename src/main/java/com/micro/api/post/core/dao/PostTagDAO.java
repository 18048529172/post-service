package com.micro.api.post.core.dao;

import com.micro.api.post.core.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/26 10:03
 */
public interface PostTagDAO extends JpaRepository<PostTag,Long> {

}
