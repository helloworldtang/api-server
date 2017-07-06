package com.tangcheng.es.controller;

import com.tangcheng.es.domain.City;
import com.tangcheng.es.query.CityQuery;
import com.tangcheng.es.query.PageQuery;
import com.tangcheng.es.service.CityESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * api-server
 *
 * @version : 2017-07-03  20:00
 */
@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityESService cityESService;

    @RequestMapping(method = RequestMethod.POST)
    public Long createCity(@RequestBody City city) {
        return cityESService.saveCity(city);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<City> searchCity(@RequestParam(value = "pageId", required = false, defaultValue = "0") Integer pageId,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return cityESService.searchCity(pageId, pageSize, searchContent);
    }

    @RequestMapping(value = "and/find", method = RequestMethod.GET)
    public List<City> findByDescriptionAndScore(@Validated CityQuery cityQuery) {
        return cityESService.findByDescriptionAndScore(cityQuery);
    }

    @RequestMapping(value = "or/find", method = RequestMethod.GET)
    public List<City> findByDescriptionOrScore(@Validated CityQuery cityQuery) {
        return cityESService.findByDescriptionOrScore(cityQuery);
    }

    @RequestMapping(value = "description/find", method = RequestMethod.GET)
    public List<City> findByDescription(@RequestParam(value = "description") String description,
                                        PageQuery pageQuery) {
        return cityESService.findByDescription(description, pageQuery);
    }

    @RequestMapping(value = "description/not/find", method = RequestMethod.GET)
    public List<City> findByDescriptionNot(@RequestParam(value = "description") String description,
                                           PageQuery pageQuery) {
        return cityESService.findByDescriptionNot(description, pageQuery);
    }

    @RequestMapping(value = "like/find", method = RequestMethod.GET)
    public List<City> findByDescriptionLike(@RequestParam(value = "description") String description,
                                            PageQuery pageQuery) {
        return cityESService.findByDescriptionLike(description, pageQuery);
    }


}
