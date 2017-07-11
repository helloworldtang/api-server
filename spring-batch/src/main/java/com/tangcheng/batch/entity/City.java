package com.tangcheng.batch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tangcheng on 7/11/2017.
 */
@Entity
public class City {
    @Id
    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
