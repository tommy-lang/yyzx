package com.htu.yyzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.R;
import com.htu.yyzx.mapper.DietaryCalerdarMapper;
import com.htu.yyzx.mapper.DietaryMapper;
import com.htu.yyzx.model.entity.Dietary;
import com.htu.yyzx.model.entity.DietaryCalerdar;
import com.htu.yyzx.model.vo.DietaryCalerdarVo;
import com.htu.yyzx.service.DietaryCalerdarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DietaryCalerdarServiceImpl extends ServiceImpl<DietaryCalerdarMapper, DietaryCalerdar> implements DietaryCalerdarService {
    @Autowired
    private DietaryCalerdarMapper dietaryCalerdarMapper;
    @Autowired
    private DietaryMapper dietaryMapper;
    @Override
    public BaseResponse<List<DietaryCalerdarVo>> listByCustomerId(Integer customerId) {
        List<DietaryCalerdar> dietaryCalerdars = dietaryCalerdarMapper.selectList(new LambdaQueryWrapper<DietaryCalerdar>().eq(customerId != -1, DietaryCalerdar::getCustomerId, customerId));
        List<DietaryCalerdarVo> collect = dietaryCalerdars.stream().map(
                item -> {
                    DietaryCalerdarVo dietaryCalerdarVo = new DietaryCalerdarVo();
                    BeanUtils.copyProperties(item,dietaryCalerdarVo);
                    Dietary dietary = dietaryMapper.selectById(item.getDietaryId());
                    dietaryCalerdarVo.setDietaryName(dietary.getName());
                    return dietaryCalerdarVo;
                }).collect(Collectors.toList());
        return R.success(collect);
    }
}
