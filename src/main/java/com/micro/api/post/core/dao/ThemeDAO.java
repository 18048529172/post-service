package com.micro.api.post.core.dao;

import com.micro.api.post.core.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/26 10:38
 */
public interface ThemeDAO extends JpaRepository<Theme,Long>,JpaSpecificationExecutor<Theme>{

    /**
     * 统计主题名称是否重复
     * @param name : 名称
     * @param tenementId : 租户id
     * @return
     */
    long countByNameAndTenementIdAndIsDeleteFalse(@NotNull String name, @NotNull String tenementId);

    /**
     * 更加租户id和id查询一个主题
     *
     * @param id
     * @param tenementId : 租户id
     * @return
     */
    Theme findOneByIdAndTenementId( @NotNull Long id, @NotNull String tenementId);

    /**
     *  更新状态为删除
     * @param tenementId
     * @param id
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update #{#entityName} set isDelete=true where tenementId=:tenementId and id=:id")
    void updateDeleteByTenementIdAndId(@NotNull @Param("tenementId") String tenementId, @NotNull @Param("id")Long id);

}
