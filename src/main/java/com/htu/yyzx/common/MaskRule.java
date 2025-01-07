package com.htu.yyzx.common;

import lombok.Data;

@Data
public class MaskRule {
    /**
     * 字段英文名称
     */

    private String name;
    /**
     * 0：隐藏，1：显示
     */

    private Integer type;
    /**
     * 规则：开头:0 中间:1 末尾: -1 全部: 2 区间：3
     */

    private Integer scope;
    /**
     * 位数
     */
    private Integer count;
    /**
     * 开始位数
     */
    private Integer start;

    /**
     * 结束位数
     */
    private Integer end;
}
