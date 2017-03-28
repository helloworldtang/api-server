package com.api.mybatis.biz;

import com.api.mybatis.mapper.CityDoMapper;
import com.api.mybatis.model.CityDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tangcheng on 3/28/2017.
 */
@Repository
public class CityBiz {
    @Autowired
    private CityDoMapper cityDoMapper;

    public Long insert(String name, String state) {
        CityDo cityDo = new CityDo();
        cityDo.setName(name);
        cityDo.setState(state);
        cityDoMapper.insertUseGeneratedKeys(cityDo);
        return cityDo.getId();
    }

    public List<CityDo> selectAll() {
        return cityDoMapper.selectAll();
    }
}
