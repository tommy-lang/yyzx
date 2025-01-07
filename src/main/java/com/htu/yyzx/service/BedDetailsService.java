package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.model.dto.room.BedChangeUpdateRequest;
import com.htu.yyzx.model.dto.room.BedDetailsUpdateRequest;
import com.htu.yyzx.model.dto.room.RoomDetailsQueryRequest;
import com.htu.yyzx.model.entity.BedDetails;
import com.htu.yyzx.model.vo.BedDetailsVO;

public interface BedDetailsService extends IService<BedDetails> {

    BaseResponse<Page<BedDetailsVO>> queryBedDetails(RoomDetailsQueryRequest request);

    BaseResponse<Page<BedDetailsVO>> updateRoomDetails(BedDetailsUpdateRequest bedDetailsUpdateRequest);

    BaseResponse<String> changeBed(BedChangeUpdateRequest bedChangeUpdateRequest);

    BaseResponse<String> deleteBedDetails(DeleteRequestBody deleteRequest);
}
