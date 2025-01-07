package com.htu.yyzx.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Dietary {
    private Integer id;
    private Integer isDeleted;
    private String type;
    private String name;
    private Double price;
    private String Islamic;
    private String picture;
    private Date createOn;
}
