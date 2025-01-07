package com.htu.yyzx.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.ErrorCode;
import com.htu.yyzx.common.R;
import com.htu.yyzx.mapper.NurseLevelMapper;
import com.htu.yyzx.model.dto.nurse.NurseLevelQueryRequest;
import com.htu.yyzx.model.entity.NurseLevel;
import com.htu.yyzx.service.NurseLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NurseLevelServiceImpl extends ServiceImpl<NurseLevelMapper, NurseLevel>
        implements NurseLevelService {

    private final NurseLevelMapper nurseLevelMapper;

    /**
     * 获取全部护理等级
     *
     * @return
     */
    @Override
    public BaseResponse<List<String>> queryAllLevel() {
        List<String> collect = lambdaQuery().select(NurseLevel::getLevelName).list()
                .stream()
                .map(NurseLevel::getLevelName)
                .toList();
        return R.success(collect);
    }

    /**
     * 分页查询护理等级
     *
     * @param nurseLevelQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<NurseLevel>> queryNurseLevel(NurseLevelQueryRequest nurseLevelQueryRequest) {
        String levelName = nurseLevelQueryRequest.getLevelName();
        Integer levelStatus = nurseLevelQueryRequest.getLevelStatus();
        int current = nurseLevelQueryRequest.getCurrent();
        int pageSize = nurseLevelQueryRequest.getPageSize();
        String sortField = nurseLevelQueryRequest.getSortField();
        String sortOrder = nurseLevelQueryRequest.getSortOrder();

        Page<NurseLevel> page = page(new Page<>(current, pageSize), new LambdaQueryWrapper<NurseLevel>()
                .like(StrUtil.isNotBlank(levelName), NurseLevel::getLevelName, levelName)
                .eq(levelStatus != null && levelStatus != -1, NurseLevel::getLevelStatus, levelStatus));
        return R.success(page);
    }

    /**
     * 添加护理水平
     *
     * @param nurseLevel
     * @return
     */
    @Override
    public BaseResponse<String> addNurseLevel(NurseLevel nurseLevel) {

        // 将 id 和 isDeleted 设置为 null 和 0
        nurseLevel.setId(null);
        nurseLevel.setIsDeleted(0);
        // 插入护理水平表
        int insert = baseMapper.insert(nurseLevel);
        if (insert != 0) {
            return R.success(null, "添加护理等级成功");
        }
        return R.error(ErrorCode.OPERATION_ERROR, "添加护理登记失败");
    }

    /**
     * 删除护理等级
     *
     * @param deleteRequest
     * @return
     */
    @Override
    public BaseResponse<String> deleteNurseLevel(DeleteRequestBody deleteRequest) {
        if (deleteRequest.getIds().size() == 1) {
            removeById(deleteRequest.getIds().get(0));
        } else {
            baseMapper.deleteBatchIds(deleteRequest.getIds());
        }
        return R.success(null, "删除成功");
    }
}




