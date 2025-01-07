package com.htu.yyzx.model.vo;

import com.htu.yyzx.model.entity.Customer;
import lombok.Data;

import java.util.List;

@Data
public class NoPreferenceVO {

    private List<Customer> customers;

    // @Data
    // class NoPreference {
    //     private Integer id;
    //     private String customName;
    // }

}
