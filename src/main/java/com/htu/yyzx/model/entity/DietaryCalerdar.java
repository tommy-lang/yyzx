package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DietaryCalerdar implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer dietaryId;
    private String dietaryType;
    private String taste;
    private Date createOn;
    private Integer customerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private static final long serialVersionUID = 1L;
}
