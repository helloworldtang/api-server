package com.tangcheng.es.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * api-server
 * 注意
 * a. City 属性名不支持驼峰式。
 * b. indexName 配置必须是全部小写，不然会出异常。
 * org.elasticsearch.indices.InvalidIndexNameException: Invalid index name [provinceIndex], must be lowercase
 *
 * @version : 2017-07-03  19:56
 */
@Document(indexName = "city", type = "city")
public class City {
    @Id
    private Long id;
    private String name;
    private String description;
    private Integer score;
    private Date createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

}
