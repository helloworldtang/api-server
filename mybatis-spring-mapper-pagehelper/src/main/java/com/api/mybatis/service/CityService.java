package com.api.mybatis.service;

import com.api.mybatis.biz.CityBiz;
import com.api.mybatis.model.CityDo;
import com.api.mybatis.model.CityVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangcheng on 3/28/2017.
 */
@Service
public class CityService implements ICityService {
    @Autowired
    private CityBiz cityBiz;

    @Override
    public Long insert(String name, String state) {
        return cityBiz.insert(name, state);
    }

    @Override
    public List<CityVo> selectAll(int pageId, int pageSize) {
        PageHelper.startPage(pageId, pageSize);
        List<CityDo> cityDoList = cityBiz.selectAll();
        List<CityVo> cityVoList = new ArrayList<>();
        for (CityDo cityDo : cityDoList) {
            CityVo cityVo = new CityVo();
            BeanUtils.copyProperties(cityDo, cityVo);
            cityVoList.add(cityVo);
        }
        return cityVoList;
    }
}
