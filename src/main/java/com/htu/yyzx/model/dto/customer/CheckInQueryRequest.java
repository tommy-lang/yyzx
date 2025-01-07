package com.htu.yyzx.model.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.htu.yyzx.common.PageRequest;
import lombok.Data;

import java.util.Date;

@Data
public class CheckInQueryRequest extends PageRequest {

    /**
     * id
     */
    private Integer id;

    /**
     * 自理老人 or 护理老人 CAN or NOTCAN
     */
    private String oldStatus;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 性别  0：男  1：女
     */
    private Integer customerSex;
    /**
     * 身份证号
     */
    private String idcard;
    /**
     * 房间号
     */
    private String roomNo;

    /**
     * 入住时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date checkinDate;
    /**
     * 合同到期时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date expirationDate;
    /**
     * 联系电话
     */
    private String contactTel;

}
