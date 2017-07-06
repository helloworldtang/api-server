package com.tangcheng.es.query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * api-server
 *
 * @author : tang.cheng
 * @version : 2017-07-06  19:54
 */
public class PageQuery {
    private Integer pageId = 0;
    private Integer pageSize = 10;

    public Pageable getPageable() {
        return new PageRequest(pageId, pageSize);
    }


    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
