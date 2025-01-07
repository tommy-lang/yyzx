package com.htu.yyzx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.R;
import com.htu.yyzx.mapper.BedMapper;
import com.htu.yyzx.model.entity.Bed;
import com.htu.yyzx.service.BedService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedServiceImpl extends ServiceImpl<BedMapper, Bed>
        implements BedService {

    /**
     * 获取指定房间号的所有床位号
     *
     * @param roomNo
     * @return
     */
    @Override
    public BaseResponse<List<String>> getAllBedsByRoomNo(String roomNo) {
        List<String> list = lambdaQuery()
                .eq(Bed::getRoomNo, roomNo)
                .list().stream().map(Bed::getBedNo).toList();
        return R.success(list);
    }
}




