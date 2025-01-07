package com.htu.yyzx.model.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OutwardApprovalRequest {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    private Integer isDeleted;

    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 外出事由
     */
    private String outgoingReason;

    /**
     * 外出时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date outgoingTime;

    /**
     * 预计回院时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date expectedReturnTime;

    /**
     * 实际回院时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date actualrReturnTime;

    /**
     * 陪同人
     */
    private String escorted;

    /**
     * 与老人关系
     */
    private String relation;

    /**
     * 陪同人电话
     */
    private String escortedTel;

    /**
     * 审批状态  0-已提交 1-同意  2-拒绝
     */
    private Integer auditStatus;

    /**
     * 审批人
     */
    private String auditPerson;

    /**
     * 审批时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date auditTime;
}