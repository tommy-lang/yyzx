package com.htu.yyzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.R;
import com.htu.yyzx.mapper.BedMapper;
import com.htu.yyzx.mapper.RoomMapper;
import com.htu.yyzx.model.entity.Bed;
import com.htu.yyzx.model.entity.Room;
import com.htu.yyzx.model.enums.BedStatus;
import com.htu.yyzx.model.vo.CascaderTreeVO;
import com.htu.yyzx.model.vo.RoomVO;
import com.htu.yyzx.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room>
        implements RoomService {

    private final BedMapper bedMapper;

    /**
     * 根据楼层查询所有房间
     *
     * @return
     */
    @Override
    public BaseResponse queryAllRoomsByFloor(String roomFloor) {
        List<Room> rooms = lambdaQuery().eq(Room::getRoomFloor, roomFloor).list();
        RoomVO roomVO = new RoomVO();
        roomVO.setRooms(rooms);
        for (Room room : roomVO.getRooms()) {
            List<Bed> beds = bedMapper.selectList(new QueryWrapper<Bed>().eq("roomNo", room.getRoomNo()));
            roomVO.setAll(roomVO.getAll() + beds.size());
            beds.forEach(bed -> {
                if (bed.getStatus() == 1) {
                    roomVO.setKx(roomVO.getKx() + 1);
                } else if (bed.getStatus() == 2) {
                    roomVO.setYr(roomVO.getYr() + 1);
                } else {
                    roomVO.setWc(roomVO.getWc() + 1);
                }
            });
            room.setBeds(beds);
        }
        return R.success(roomVO);
    }

    /**
     * 获取所有房间
     *
     * @return
     */
    @Override
    public BaseResponse<List<String>> getAllRooms() {
        List<String> list = lambdaQuery()
                .list()
                .stream()
                .map(Room::getRoomNo)
                .toList();
        return R.success(list);
    }

    /**
     * 获取所有房间
     *
     * @return
     */
    @Override
    public BaseResponse queryAllRooms() {
        List<Room> rooms = lambdaQuery().list();
        List<CascaderTreeVO> cascaderTreeVOS = rooms.stream().map(this::roomToCascaderTreeVO).toList();
        return R.success(cascaderTreeVOS);
    }

    /**
     * room 结构转为 CascaderTreeVO 结构
     * @param room
     * @return
     */
    private CascaderTreeVO roomToCascaderTreeVO(Room room) {
        CascaderTreeVO roomTree = new CascaderTreeVO();
        List<CascaderTreeVO> bedNoTree = bedMapper.selectList(new QueryWrapper<Bed>().eq("roomNo", room.getRoomNo()))
                .stream().map(RoomServiceImpl::bedToCascaderTreeVO)
                .toList();
        roomTree.setLabel(room.getRoomNo());
        roomTree.setValue(room.getRoomNo());
        roomTree.setChildren(bedNoTree);
        return roomTree;
    }

    /**
     * Bed 结构转为 CascaderTreeVO 结构
     *
     * @param bed
     * @return
     */
    private static CascaderTreeVO bedToCascaderTreeVO(Bed bed) {
        CascaderTreeVO bedTree = new CascaderTreeVO();
        bedTree.setValue(bed.getBedNo());
        bedTree.setLabel(bed.getBedNo());
        bedTree.setStatus(BedStatus.OCCUPATION.getCode() == bed.getStatus());
        return bedTree;
    }
}




