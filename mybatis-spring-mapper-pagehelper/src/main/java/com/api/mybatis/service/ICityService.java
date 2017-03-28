package com.api.mybatis.service;

import com.api.mybatis.model.CityVo;

import java.util.List;

/**
 * Created by tangcheng on 3/28/2017.
 */
public interface ICityService {
    Long insert(String name, String state);

    List<CityVo> selectAll(int pageId, int pageSize);
}
