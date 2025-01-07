package com.htu.yyzx.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.ErrorCode;
import com.htu.yyzx.common.R;
import com.htu.yyzx.mapper.CustomerNurseItemMapper;
import com.htu.yyzx.mapper.NurseContentMapper;
import com.htu.yyzx.mapper.NurseLevelItemMapper;
import com.htu.yyzx.mapper.NurseLevelMapper;
import com.htu.yyzx.model.dto.healthcare.BoughtOrNotNurseServiceQueryRequest;
import com.htu.yyzx.model.dto.nurse.NurseContentQueryRequest;
import com.htu.yyzx.model.entity.CustomerNurseItem;
import com.htu.yyzx.model.entity.NurseContent;
import com.htu.yyzx.model.entity.NurseLevelItem;
import com.htu.yyzx.model.enums.LevelStatus;
import com.htu.yyzx.service.NurseContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NurseContentServiceImpl extends ServiceImpl<NurseContentMapper, NurseContent>
        implements NurseContentService {
    // 护理项目 Mapper
    private final NurseContentMapper nurseContentMapper;
    private final NurseLevelItemMapper nurseLevelItemMapper;
    private final CustomerNurseItemMapper customerNurseItemMapper;

    @Autowired
    private NurseLevelMapper nurseLevelMapper;

    /**
     * 查询护理项目通过级别名
     *
     * @param nurseContentQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<NurseContent>> queryNurseContentbylevelName(NurseContentQueryRequest nurseContentQueryRequest) {
        String serialNumber = nurseContentQueryRequest.getSerialNumber();
        String nursingName = nurseContentQueryRequest.getNursingName();
        Integer status = nurseContentQueryRequest.getStatus();
        int current = nurseContentQueryRequest.getCurrent();
        int pageSize = nurseContentQueryRequest.getPageSize();
        String sortField = nurseContentQueryRequest.getSortField();
        String sortOrder = nurseContentQueryRequest.getSortOrder();
        String levelName = nurseContentQueryRequest.getLevelName();
        List<Integer> itemIdByLevelName = nurseLevelItemMapper.getItemIdByLevelName(levelName);
        //
//
        LambdaQueryWrapper<NurseContent> eq = new LambdaQueryWrapper<NurseContent>()
                .like(StrUtil.isNotBlank(serialNumber), NurseContent::getSerialNumber, serialNumber)
                .like(StrUtil.isNotBlank(nursingName), NurseContent::getNursingName, nursingName)
                .eq(status != null && status != -1, NurseContent::getStatus, status);

        // 如果 itemIdByLevelName 非空，则添加 in 条件
        if (itemIdByLevelName != null && !itemIdByLevelName.isEmpty()) {
            eq.in(NurseContent::getId, itemIdByLevelName);
            Page<NurseContent> page = page(new Page<>(current, pageSize), eq);
            return R.success(page);
        } else return R.error(500, "此级护理还未添加护理项目");

    }

    @Override
    public BaseResponse<Page<NurseContent>> queryNurseContent(NurseContentQueryRequest nurseContentQueryRequest) {
        String serialNumber = nurseContentQueryRequest.getSerialNumber();
        String nursingName = nurseContentQueryRequest.getNursingName();
        Integer status = nurseContentQueryRequest.getStatus();
        int current = nurseContentQueryRequest.getCurrent();
        int pageSize = nurseContentQueryRequest.getPageSize();
        String sortField = nurseContentQueryRequest.getSortField();
        String sortOrder = nurseContentQueryRequest.getSortOrder();
        //
//
        LambdaQueryWrapper<NurseContent> eq = new LambdaQueryWrapper<NurseContent>()
                .like(StrUtil.isNotBlank(serialNumber), NurseContent::getSerialNumber, serialNumber)
                .like(StrUtil.isNotBlank(nursingName), NurseContent::getNursingName, nursingName)
                .eq(status != null && status != -1, NurseContent::getStatus, status);

        // 如果 itemIdByLevelName 非空，则添加 in 条件
        Page<NurseContent> page = page(new Page<>(current, pageSize), eq);
        return R.success(page);
    }

    /**
     * 添加护理水平
     *
     * @param nurseContent
     * @return
     */
    @Override
    public BaseResponse<String> addNurseContent(NurseContent nurseContent) {

        // 将 id 和 isDeleted 设置为 null 和 0
        nurseContent.setId(null);
        nurseContent.setIsDeleted(0);
        // 插入护理水平表
        int insert = baseMapper.insert(nurseContent);
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
    public BaseResponse<String> deleteNurseContent(DeleteRequestBody deleteRequest) {
        if (deleteRequest.getIds().size() == 1) {
            removeById(deleteRequest.getIds().get(0));
        } else {
            baseMapper.deleteBatchIds(deleteRequest.getIds());
        }
        return R.success(null, "删除成功");
    }

    /**
     * 根据护理级别名称返回对应的护理内容（分页）
     *
     * @param levelName
     * @return
     */
    @Override
    public BaseResponse<String> queryNurseContentByLevelName(String levelName) {


        return null;
    }

    /**
     * 根据Levelid和Itemid删除关联关系
     *
     * @param nurseLevelItem
     * @return
     */
    @Override
    public BaseResponse<String> deleteNurseCnstentByLevelIdAndItemId(NurseLevelItem nurseLevelItem) {
        Map<String, Object> map = new HashMap<>();
        map.put("LevelId", nurseLevelItem.getLevelId());
        map.put("ItemId", nurseLevelItem.getItemId());
        int i = nurseLevelItemMapper.deleteByMap(map);
        if (i > 0) {
            return R.success(null, "删除成功");
        } else {
            return R.error(null, "删除失败");
        }

    }

    /**
     * 查询除了此级护理项目之外的项目
     *
     * @param id
     * @return
     */
    @Override
    public BaseResponse<List<NurseContent>> getNurseContentList(Integer id) {
        List<NurseLevelItem> nurseLevelItems = nurseLevelItemMapper.selectList(new LambdaQueryWrapper<NurseLevelItem>().eq(id != null, NurseLevelItem::getLevelId, id));
        List<Integer> collect = nurseLevelItems.stream()
                .map(NurseLevelItem::getItemId)
                .collect(Collectors.toList());
        if (collect.size() >= 1) {
            List<NurseContent> nurseContents = nurseContentMapper.selectList(
                    new LambdaQueryWrapper<NurseContent>().notIn(NurseContent::getId, collect)
            );
            return R.success(nurseContents, "查询成功");
        } else {
            List<NurseContent> nurseContents = nurseContentMapper.selectList(new LambdaQueryWrapper<>());
            nurseContents.forEach(System.out::println);
            return R.success(nurseContents, "查询成功");
        }
    }

    /**
     * 根据客户id分页查询未购买的护理服务
     *
     * @param boughtOrNotNurseServiceQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<NurseContent>> queryNotBoughtNurseService(BoughtOrNotNurseServiceQueryRequest boughtOrNotNurseServiceQueryRequest) {
        int current = boughtOrNotNurseServiceQueryRequest.getCurrent();
        int pageSize = boughtOrNotNurseServiceQueryRequest.getPageSize();
        String sortField = boughtOrNotNurseServiceQueryRequest.getSortField();
        String sortOrder = boughtOrNotNurseServiceQueryRequest.getSortOrder();
        List<Integer> ids = new ArrayList<>();
        Optional.ofNullable(boughtOrNotNurseServiceQueryRequest.getCustomerId())
                        .ifPresent(customerId -> {
                            ids.addAll(customerNurseItemMapper.selectList(new LambdaQueryWrapper<CustomerNurseItem>()
                                            .eq(CustomerNurseItem::getCustomerId, customerId))
                                    .stream()
                                    .map(CustomerNurseItem::getItemId)
                                    .toList());
                        });
        Page<NurseContent> page = page(new Page<>(current, pageSize), new LambdaQueryWrapper<NurseContent>()
                .notIn(!ids.isEmpty(), NurseContent::getId, ids)
                .eq(NurseContent::getStatus, LevelStatus.DIE.getCode()));
        return R.success(page, "查询成功");
    }
}




