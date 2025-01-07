package com.htu.yyzx.mapper;

import com.htu.yyzx.model.entity.CustomerPreference;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface CustomerPreferenceMapper extends BaseMapper<CustomerPreference> {
    List<CustomerPreference> findByCustomerPreferencePage(String customerName);
}




