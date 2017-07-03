package com.tangcheng.es.repository;

import com.tangcheng.es.domain.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * api-server
 *
 * @version : 2017-07-03  19:58
 */
public interface CityRepository extends ElasticsearchRepository<City,Long>{
}
