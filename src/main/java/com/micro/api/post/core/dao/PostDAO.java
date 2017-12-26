package com.micro.api.post.core.dao;

import com.micro.api.post.core.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostDAO extends JpaRepository<Post,Long>,JpaSpecificationExecutor<Post> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update Post set isDelete=:isDelete where id=:id")
    void updateIsDelete(@Param("id") Long id,@Param("isDelete") Boolean isDelete);


}
