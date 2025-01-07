package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @TableName room
 */
@Data
@TableName(value = "room")
public class Room implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 房间楼层
     */
    private String roomFloor;
    /**
     * 房间号
     */
    private String roomNo;
    @TableField(exist = false)
    private List<Bed> beds;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private Integer createBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新者
     */
    private Integer updateBy;
    /**
     * 是否删除
     */
    private Integer isDelete;
}