package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.model.entity.Room;

import java.util.List;

public interface RoomService extends IService<Room> {

    BaseResponse queryAllRoomsByFloor(String roomFloor);

    BaseResponse<List<String>> getAllRooms();

    BaseResponse queryAllRooms();
}
