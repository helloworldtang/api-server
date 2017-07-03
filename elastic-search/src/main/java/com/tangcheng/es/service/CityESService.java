package com.tangcheng.es.service;

import com.tangcheng.es.domain.City;

import java.util.List;

/**
 * api-server
 *
 * @version : 2017-07-03  19:59
 */
public interface CityESService {
    Long saveCity(City city);

    List<City> searchCity(Integer pageId, Integer pageSize, String searchContent);
}
