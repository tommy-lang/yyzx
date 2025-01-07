package com.htu.yyzx;

import com.htu.yyzx.mapper.CustomerPreferenceMapper;
import com.htu.yyzx.model.entity.CustomerPreference;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

class YyzxApplicationTests {

    @Resource
    private CustomerPreferenceMapper customerPreferenceMapper;

    @Test
    void contextLoads() {
        List<CustomerPreference> customerPreferencePage = customerPreferenceMapper.findByCustomerPreferencePage(null);
        System.out.println(customerPreferencePage);
    }

}
