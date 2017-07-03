package com.tangcheng.es.service;

import com.tangcheng.es.domain.City;
import com.tangcheng.es.repository.CityRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * api-server
 *
 * @version : 2017-07-03  20:00
 */
@Service
public class CityESServiceImpl implements CityESService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Long saveCity(City city) {
        City save = cityRepository.save(city);
        return save.getId();
    }

    @Override
    public List<City> searchCity(Integer pageId, Integer pageSize, String searchContent) {
        // Function Score Query
        String SCORE_MODE_SUM = "sum";// 权重分求和模式
        Float MIN_SCORE = 10.0F;// 由于无相关性的分值默认为 1 ，设置权重分最小值为 10
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchPhraseQuery("name", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.matchPhraseQuery("description", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(500))
                .scoreMode(SCORE_MODE_SUM)
                .setMinScore(MIN_SCORE);
        // 分页参数
        Pageable pageable = new PageRequest(pageId, pageSize);
        // 创建搜索 DSL 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();

        LOGGER.info("searchCity(): searchContent [{}] DSL  ={}", searchContent, searchQuery.getQuery().toString());
        Page<City> searchPageResults = cityRepository.search(searchQuery);
        return searchPageResults.getContent();
    }
}
