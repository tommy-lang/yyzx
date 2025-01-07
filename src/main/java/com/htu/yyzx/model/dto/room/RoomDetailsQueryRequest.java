package com.htu.yyzx.model.dto.room;

import com.htu.yyzx.common.PageRequest;
import com.htu.yyzx.constant.UsingOrHistoryConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoomDetailsQueryRequest extends PageRequest {
    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 客户姓名
     */
    private String customer;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 使用中 or 历史记录 （USING or HISTORY）
     */
    private String usingOrHistory = UsingOrHistoryConstant.USING;
}
