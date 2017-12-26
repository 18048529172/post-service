package com.micro.api.post.core.service;

import com.micro.api.post.core.dao.specification.PostSpecification;
import com.micro.api.post.core.entity.Post;
import com.micro.api.post.dto.AddPost;
import com.micro.api.post.dto.UpdatePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PostService {

    Long save(AddPost post);

    Post findById(Long id);

    Page<Post> page(PostSpecification postSpecification,PageRequest pageRequest);

    void delete(Long id);

    void update(UpdatePost updatePost);
}
