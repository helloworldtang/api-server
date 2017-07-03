package com.tangcheng.es.controller;

import com.tangcheng.es.domain.City;
import com.tangcheng.es.service.CityESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * api-server
 *
 * @version : 2017-07-03  20:00
 */
@RestController
public class CityController {

    @Autowired
    private CityESService cityESService;
    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public Long createCity(@RequestBody City city) {
        return cityESService.saveCity(city);
    }

    @RequestMapping(value = "/api/city/search", method = RequestMethod.GET)
    public List<City> searchCity(@RequestParam(value = "pageId", required = false, defaultValue = "0") Integer pageId,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return cityESService.searchCity(pageId, pageSize, searchContent);
    }

}
