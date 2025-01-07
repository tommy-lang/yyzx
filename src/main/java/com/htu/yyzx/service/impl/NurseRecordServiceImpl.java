package com.htu.yyzx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.R;
import com.htu.yyzx.mapper.NurseContentMapper;
import com.htu.yyzx.mapper.NurseRecordMapper;
import com.htu.yyzx.mapper.UserMapper;
import com.htu.yyzx.model.dto.nurserecord.NurseRecordQueryRequest;
import com.htu.yyzx.model.entity.NurseContent;
import com.htu.yyzx.model.entity.NurseRecord;
import com.htu.yyzx.model.entity.User;
import com.htu.yyzx.model.vo.NurseRecordVO;
import com.htu.yyzx.service.NurseRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NurseRecordServiceImpl extends ServiceImpl<NurseRecordMapper, NurseRecord>
        implements NurseRecordService {

    private final NurseRecordMapper nurseRecordMapper;
    private final NurseContentMapper nurseContentMapper;
    private final UserMapper userMapper;

    /**
     * 查询护理记录
     *
     * @param nurseRecordQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<NurseRecordVO>> queryNurseRecord(NurseRecordQueryRequest nurseRecordQueryRequest) {
        Integer customerId = nurseRecordQueryRequest.getCustomerId();
        int current = nurseRecordQueryRequest.getCurrent();
        int pageSize = nurseRecordQueryRequest.getPageSize();
        String sortField = nurseRecordQueryRequest.getSortField();
        String sortOrder = nurseRecordQueryRequest.getSortOrder();

        Page<NurseRecord> page = page(new Page<>(current, pageSize), new LambdaQueryWrapper<NurseRecord>()
                .eq(customerId != null, NurseRecord::getCustomerId, customerId));
        Page<NurseRecordVO> nurseRecordVOPage = new Page<>(current, pageSize, page.getTotal());
        nurseRecordVOPage.setRecords(page.getRecords().stream().map(nurseRecord -> {
            NurseRecordVO nurseRecordVO = BeanUtil.copyProperties(nurseRecord, NurseRecordVO.class);

            Optional.of(nurseRecord.getUserId())
                    .ifPresent(userId -> {
                        User user = userMapper.selectById(userId);
                        String nickname = user.getNickname();
                        String phoneNumber = user.getPhoneNumber();
                        nurseRecordVO.setUserNickName(nickname);
                        nurseRecordVO.setContactTel(phoneNumber);
                    });

            Optional.ofNullable(nurseRecord.getItemId())
                    .ifPresent(itemId -> {
                        NurseContent nurseContent = nurseContentMapper.selectById(itemId);
                        String nursingName = nurseContent.getNursingName();
                        String message = nurseContent.getMessage();
                        String serialNumber = nurseContent.getSerialNumber();
                        nurseRecordVO.setSerialNumber(serialNumber);
                        nurseRecordVO.setNursingContent(message);
                        nurseRecordVO.setItemName(nursingName);
                    });
            return nurseRecordVO;
        }).toList());

        return R.success(nurseRecordVOPage, "查询成功");
    }

    /**
     * 删除
     *
     * @param deleteRequestBody
     * @return
     */
    @Override
    public BaseResponse<Page<NurseRecordVO>> deleteNurseRecord(DeleteRequestBody deleteRequestBody) {
        if (deleteRequestBody.getIds().size() == 1) {
            this.removeById(deleteRequestBody.getIds().get(0));
        } else {
            nurseRecordMapper.deleteBatchIds(deleteRequestBody.getIds());
        }
        return R.success(null, "删除成功");
    }

    /**
     * 删除护理记录（by customerId）
     *
     * @param deleteRequestBody
     * @return
     */
    @Override
    public BaseResponse<Page<NurseRecordVO>> deleteNurseRecordByCustomerId(DeleteRequestBody deleteRequestBody) {
        List<Integer> ids = new ArrayList<>();
        deleteRequestBody.getIds().forEach(id -> {
            List<Integer> list = lambdaQuery()
                    .eq(NurseRecord::getCustomerId, id)
                    .select(NurseRecord::getId).list().stream().map(NurseRecord::getId).toList();
            ids.addAll(list);
        });
        baseMapper.deleteBatchIds(ids);
        return R.success(null, "删除成功");
    }
}




