package com.micro.api.post.core.service.impl;

import com.micro.api.post.core.dao.ThemeDAO;
import com.micro.api.post.core.entity.Theme;
import com.micro.api.post.core.service.ThemeService;
import com.micro.api.post.dto.AddThemeDTO;
import com.micro.api.post.exceptions.ExceptionChecks;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/26 13:59
 */
@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeDAO themeDAO;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Long add(AddThemeDTO addThemeDTO) {
        this.check(addThemeDTO);
        Theme theme = addThemeDTO.toEntity(addThemeDTO);
        this.builderTheme(addThemeDTO,theme);
        themeDAO.save(theme);
        return theme.getId();
    }


    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void deleteByTenementIdAndId(String tenementId, Long id) {
        this.themeDAO.updateDeleteByTenementIdAndId(tenementId,id);
    }

    private void check( AddThemeDTO addThemeDTO ) {
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(addThemeDTO.getName()),"名称不能为空");
        ExceptionChecks.checkArgument((addThemeDTO.getName().length()<=200),"名称不能大于200字符");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(addThemeDTO.getTenementId()),"租户id不能为空");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(addThemeDTO.getUserId()),"用户id不能为空");
        long nameCount = this.themeDAO.countByNameAndTenementIdAndIsDeleteFalse(addThemeDTO.getName(),addThemeDTO.getTenementId());
        ExceptionChecks.checkLogic((nameCount == 0),"名称重复");
        String memo = addThemeDTO.getMemo();
        if(StringUtils.isNotBlank(memo)){
            ExceptionChecks.checkArgument((memo.length()<=4000),"备注不能大于4000字符");
        }
        Long parentId = addThemeDTO.getParentId();
        if(parentId != null){
            Theme theme = this.themeDAO.findOneByIdAndTenementId(parentId,addThemeDTO.getTenementId());
            ExceptionChecks.checkArgument((theme != null),"父id:"+parentId+"，不存在");
        }
    }

    private void builderTheme( AddThemeDTO addThemeDTO, Theme theme ) {
        Long parentId = addThemeDTO.getParentId();
        if(parentId != null){
            theme.setParent(this.themeDAO.findOneByIdAndTenementId(parentId,addThemeDTO.getTenementId()));
        }
        //设置用户名称


    }
}
