package com.htu.yyzx.model.vo;

import com.htu.yyzx.model.entity.Room;
import lombok.Data;

import java.util.List;

@Data
public class RoomVO {
    private Long all = 0L;
    /**
     * 空闲
     */
    private Long kx = 0L;
    /**
     * 有人
     */
    private Long yr = 0L;
    /**
     * 外出
     */
    private Long wc = 0L;

    // 指定楼层的房间
    private List<Room> rooms;

}
