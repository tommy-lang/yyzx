package com.htu.yyzx.model.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CheckOutApprovalRequest implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 退住时间
     */
    @JsonFormat(timezone = "GTM+8", pattern = "yyyy-MM-dd")
    private Date retreatTime;

    /**
     * 退住类型 0-正常退住  1-死亡退住 2-保留床位
     */
    private Integer retreatType;

    /**
     * 退住原因
     */
    private String retreatReason;

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
    @JsonFormat(timezone = "GTM+8", pattern = "yyyy-MM-dd")
    private Date auditTime;

}