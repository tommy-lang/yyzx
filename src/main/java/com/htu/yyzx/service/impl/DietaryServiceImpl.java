package com.htu.yyzx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.mapper.DietaryMapper;
import com.htu.yyzx.model.entity.Dietary;
import com.htu.yyzx.service.DietaryService;
import org.springframework.stereotype.Service;

@Service
public class DietaryServiceImpl extends ServiceImpl<DietaryMapper, Dietary> implements DietaryService {
}
