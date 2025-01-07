package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.model.entity.DietaryCalerdar;
import com.htu.yyzx.model.vo.DietaryCalerdarVo;

import java.util.List;

public interface DietaryCalerdarService extends IService<DietaryCalerdar> {
    BaseResponse<List<DietaryCalerdarVo>> listByCustomerId(Integer customerId);
}
