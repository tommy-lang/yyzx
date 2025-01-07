package com.htu.yyzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.model.dto.room.BedChangeUpdateRequest;
import com.htu.yyzx.model.dto.room.BedDetailsUpdateRequest;
import com.htu.yyzx.model.dto.room.RoomDetailsQueryRequest;
import com.htu.yyzx.model.vo.BedDetailsVO;
import com.htu.yyzx.service.BedDetailsService;
import com.htu.yyzx.service.BedService;
import com.htu.yyzx.service.RoomService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final BedService bedService;

    private final BedDetailsService bedDetailsService;

    /**
     * 根据楼层查询所有房间
     *
     * @param roomFloor
     * @return
     */
    @GetMapping("/getAllRoomsByFloor")
    public BaseResponse getAllRoomsByFloor(@NotNull String roomFloor) {
        return roomService.queryAllRoomsByFloor(roomFloor);
    }

    /**
     * 根据楼层查询所有房间
     *
     * @return
     */
    @GetMapping("/getAllRooms")
    public BaseResponse getAllRooms() {
        return roomService.queryAllRooms();
    }


    /**
     * 分页查询所有房间信息（包括使用情况 or 使用历史）
     *
     * @param request
     * @return
     */
    @PostMapping("/getRoomDetails")
    public BaseResponse<Page<BedDetailsVO>> getRoomDetails(
            @RequestBody RoomDetailsQueryRequest request
    ) {
        return bedDetailsService.queryBedDetails(request);
    }

    /**
     * 床位调换
     *
     * @param bedChangeUpdateRequest
     * @return
     */
    @PostMapping("/changeBed")
    public BaseResponse<String> changeBed(
            @RequestBody @Validated BedChangeUpdateRequest bedChangeUpdateRequest
    ) {
        System.out.println(bedChangeUpdateRequest.toString());
        return bedDetailsService.changeBed(bedChangeUpdateRequest);
    }

    /**
     * 更新房间详细信息
     *
     * @param bedDetailsUpdateRequest
     * @return
     */
    @PostMapping("/updateRoomDetails")
    public BaseResponse<Page<BedDetailsVO>> updateRoomDetails(
            @RequestBody @Validated BedDetailsUpdateRequest bedDetailsUpdateRequest
    ) {
        return bedDetailsService.updateRoomDetails(bedDetailsUpdateRequest);
    }

    /**
     * 删除床位详细信息
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/deleteBed")
    public BaseResponse<String> deleteBedDetails(
            @RequestBody @Validated DeleteRequestBody deleteRequest
    ) {
        return bedDetailsService.deleteBedDetails(deleteRequest);
    }
}
