package com.api.mybatis.controller;

import com.api.mybatis.model.CityVo;
import com.api.mybatis.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangcheng on 3/28/2017.
 */
@RestController
@RequestMapping(value = "city")
public class CityController {

    public static final int END = -1;

    @Autowired
    private ICityService cityService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> add(@RequestParam(value = "name") String name,
                                    @RequestParam(value = "state") String state) {
        return ResponseEntity.ok(cityService.insert(name, state));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> selectAll(@RequestParam(value = "pageId", defaultValue = "0") int pageId,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Map<String, Object> result = new HashMap<>();
        List<CityVo> voList = cityService.selectAll(pageId, pageSize);
        result.put("result", voList);

        int nextPageId = END;
        if (voList.size() == pageSize) {
            nextPageId = ++pageId;
        }
        result.put("next_page_id", nextPageId);
        result.put("pageSize", pageSize);
        return ResponseEntity.ok(result);
    }

}
