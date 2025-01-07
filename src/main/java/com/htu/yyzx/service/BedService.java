package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.model.entity.Bed;

import java.util.List;

public interface BedService extends IService<Bed> {

    BaseResponse<List<String>> getAllBedsByRoomNo(String roomNo);
}
