package com.micro.api.post.web;

import com.micro.api.post.core.entity.Post;
import com.micro.api.post.core.service.PostService;
import com.micro.api.post.utils.response.Body;
import com.micro.api.post.utils.response.Response;
import com.micro.api.post.utils.response.ResponseTemplate;
import com.micro.api.post.dto.AddPost;
import com.micro.api.post.dto.QueryPost;
import com.micro.api.post.dto.UpdatePost;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "帖子服务",tags = {"帖子API"})
@RestController
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private ResponseTemplate responseTemplate;

    @ApiOperation(value = "新增帖子",notes = "新增一条帖子,新增成功在消息体中返回id")
    @PostMapping("/post/add")
    public Response add(@RequestBody AddPost addPost){
        return responseTemplate.doResponse(()->{
            Long id = this.postService.save(addPost);
            return Body.create("id",id);
        });
    }

    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/post/query")
    public Response query(@RequestBody QueryPost queryPost){
        return this.responseTemplate.doResponse(()->{
            Page<Post> postPage = this.postService.page(queryPost.toPostSpecification(),queryPost.toPageAble());
            return Body
                    .create("totalRows",postPage.getTotalElements())
                    .append("rows",postPage.getContent())
                    .append("totalPages",postPage.getTotalPages());

        });
    }

    @ApiOperation(value = "修改",notes = "修改")
    @PostMapping("/post/update")
    public Response update(@RequestBody UpdatePost updatePost){
        return this.responseTemplate.doResponse(()->{
            postService.update(updatePost);
            return null;
        });
    }

    @ApiOperation(value = "根据id删除帖子(逻辑删除)",notes = "根据id删除帖子")
    @PostMapping("/post/delete")
    public Response delete(@RequestParam("id") Long id){
        return this.responseTemplate.doResponse(()->{
            this.postService.delete(id);
            return null;
        });
    }






}
