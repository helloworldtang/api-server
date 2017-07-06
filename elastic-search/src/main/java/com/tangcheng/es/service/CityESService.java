package com.tangcheng.es.service;

import com.tangcheng.es.domain.City;
import com.tangcheng.es.query.CityQuery;
import com.tangcheng.es.query.PageQuery;

import java.util.List;

/**
 * api-server
 *
 * @version : 2017-07-03  19:59
 */
public interface CityESService {
    Long saveCity(City city);

    List<City> searchCity(Integer pageId, Integer pageSize, String searchContent);

    List<City> findByDescriptionAndScore(CityQuery cityQuery);

    List<City> findByDescriptionOrScore(CityQuery cityQuery);

    List<City> findByDescription(String description, PageQuery pageQuery);

    List<City> findByDescriptionNot(String description, PageQuery pageQuery);

    List<City> findByDescriptionLike(String description, PageQuery pageQuery);
}
