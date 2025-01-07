package com.htu.yyzx.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class CascaderTreeVO {
    private String value;
    private String label;
    private boolean status;
    private List<CascaderTreeVO> children;
}
