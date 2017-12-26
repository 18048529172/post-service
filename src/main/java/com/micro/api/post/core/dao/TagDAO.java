package com.micro.api.post.core.dao;

import com.micro.api.post.core.entity.Post;
import com.micro.api.post.core.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/25 15:44
 */
public interface TagDAO extends JpaRepository<Tag,Long>,JpaSpecificationExecutor<Tag> {

    long countByOrgIdAndName(String orgId,String name);

    List<Tag> findByOrgIdAndIsDeleteFalseOrderByCreateTimeDesc(String orgId);

    /**
     * 修改删除状态
     * @param id ：主键
     * @param isDelete ： 修改的删除状态
     */
    @Modifying
    @Query(value = "update Tag set isDelete=:isDelete where id=:id")
    void updateDeleteStatus(@Param("id") Long id, @Param("isDelete") boolean isDelete);
}
