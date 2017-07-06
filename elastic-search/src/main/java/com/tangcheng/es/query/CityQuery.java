package com.tangcheng.es.query;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * api-server
 *
 * @author : tang.cheng
 * @version : 2017-07-06  19:54
 */
public class CityQuery extends PageQuery {
    @NotEmpty
    private String description;
    @NotEmpty
    private Integer score;

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
}
