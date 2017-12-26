package com.micro.api.post.web;

import com.micro.api.post.core.service.TagService;
import com.micro.api.post.utils.response.Body;
import com.micro.api.post.utils.response.Response;
import com.micro.api.post.utils.response.ResponseTemplate;
import com.micro.api.post.dto.AddTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/25 16:00
 */
@Api(value = "标签服务",tags = {"标签API"})
@RestController
public class TagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private ResponseTemplate responseTemplate;

    @ApiOperation(value = "查询所有标签")
    @GetMapping("/tag/{orgId}/listall")
    public Response listAll(@PathVariable("orgId") @ApiParam(value = "组织机构id",required = true) String orgId ){
        return this.responseTemplate.doResponse(()->{
            return Body.create("tags",this.tagService.findByOrgIdAndIsDeleteFase(orgId));
        });
    }

    @ApiOperation(value = "新增标签")
    @PostMapping("/tag/add")
    public Response add( @RequestBody AddTag addTag ){
        return this.responseTemplate.doResponse(()->{
            Long id = this.tagService.add(addTag);
            return Body.create("id",id);
        });
    }

    @ApiOperation(value = "删除标签")
    @PostMapping("/tag/delete/{id}")
    public Response delete( @PathVariable("id") @ApiParam(value = "主键id",required = true) Long id ){
        return this.responseTemplate.doResponse(()->{
            this.tagService.deleteById(id);
            return null;
        });
    }


}
