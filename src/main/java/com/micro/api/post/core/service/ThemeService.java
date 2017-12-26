package com.micro.api.post.core.service;

import com.micro.api.post.dto.AddThemeDTO;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/26 13:53
 */
public interface ThemeService {

    /**
     * 新增一个主题
     * @param addThemeDTO
     * @return
     */
    Long add(AddThemeDTO addThemeDTO);

    /**
     *  删除一个主题
     * @param tenementId
     * @param id
     */
    void deleteByTenementIdAndId(String tenementId, Long id);





}
