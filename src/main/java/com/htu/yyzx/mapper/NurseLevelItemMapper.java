package com.htu.yyzx.mapper;

import com.htu.yyzx.model.entity.NurseLevelItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface NurseLevelItemMapper extends BaseMapper<NurseLevelItem> {
     List<Integer> getItemIdByLevelName(String levelName);
}




