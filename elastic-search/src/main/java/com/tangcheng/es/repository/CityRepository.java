package com.tangcheng.es.repository;

import com.tangcheng.es.domain.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * api-server
 *
 * @version : 2017-07-03  19:58
 */
public interface CityRepository extends ElasticsearchRepository<City,Long>{
    List<City> findByDescriptionAndScore(String description, Integer score);

    List<City> findByDescriptionOrScore(String description, Integer score);

    Slice<City> findByDescription(String description, Pageable pageable);

    Slice<City> findByDescriptionNot(String description, Pageable pageable);

    Slice<City> findByDescriptionLike(String description, Pageable pageable);
}
