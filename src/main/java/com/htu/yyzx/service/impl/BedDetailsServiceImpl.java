package com.htu.yyzx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.R;
import com.htu.yyzx.constant.UsingOrHistoryConstant;
import com.htu.yyzx.mapper.BedDetailsMapper;
import com.htu.yyzx.mapper.BedMapper;
import com.htu.yyzx.mapper.CustomerMapper;
import com.htu.yyzx.model.dto.room.BedChangeUpdateRequest;
import com.htu.yyzx.model.dto.room.BedDetailsUpdateRequest;
import com.htu.yyzx.model.dto.room.RoomDetailsQueryRequest;
import com.htu.yyzx.model.entity.Bed;
import com.htu.yyzx.model.entity.BedDetails;
import com.htu.yyzx.model.entity.Customer;
import com.htu.yyzx.model.enums.BedStatus;
import com.htu.yyzx.model.vo.BedDetailsVO;
import com.htu.yyzx.service.BedDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BedDetailsServiceImpl extends ServiceImpl<BedDetailsMapper, BedDetails>
        implements BedDetailsService {

    private final CustomerMapper customerMapper;
    private final BedMapper bedMapper;

    /**
     * 获得床位详细信息查询构造器
     *
     * @param startDate
     * @param endDate
     * @param customers
     * @return
     */
    private static QueryWrapper<BedDetails> getBedDetailsQueryWrapper(String startDate, String endDate, List<Integer> customers, String usingOrHistory) {
        QueryWrapper<BedDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt(StrUtil.isNotBlank(startDate) && !"Invalid Date".equals(startDate), "startDate", startDate)
                .lt(StrUtil.isNotBlank(endDate) && !"Invalid Date".equals(endDate), "endDate", endDate);

        if (UsingOrHistoryConstant.USING.equals(usingOrHistory)) {
            queryWrapper.gt("endDate", new Date());
        } else {
            queryWrapper.lt("endDate", new Date());
        }
        queryWrapper.in(customers != null,
                "customerId",
                customers
        );
        return queryWrapper;
    }

    @Override
    public BaseResponse<Page<BedDetailsVO>> queryBedDetails(RoomDetailsQueryRequest request) {
        String startDate = request.getStartDate();
        String endDate = request.getEndDate();
        String customerName = request.getCustomer();
        Integer sex = request.getSex();
        // 根据部分客户姓名 - 获取客户id（模糊查询）
        List<Integer> customers = null;
        if (customerName != null) {
            customers = getCustomerIdsByName(customerName, sex);
        }
        String usingOrHistory = request.getUsingOrHistory();
        int current = request.getCurrent();
        int pageSize = request.getPageSize();
        String sortField = request.getSortField();
        String sortOrder = request.getSortOrder();

        QueryWrapper<BedDetails> queryWrapper = getBedDetailsQueryWrapper(startDate, endDate, customers, usingOrHistory);
        Page<BedDetailsVO> roomDetailsVOPage = getBedDetailsVOPage(current, pageSize, queryWrapper);
        return R.success(roomDetailsVOPage);
    }

    /**
     * 更新床位详细信息
     *
     * @param bedDetailsUpdateRequest
     * @return
     */
    @Override
    @Transactional
    public BaseResponse updateRoomDetails(BedDetailsUpdateRequest bedDetailsUpdateRequest) {
        String customerName = bedDetailsUpdateRequest.getCustomerName();
        Integer customerSex = bedDetailsUpdateRequest.getCustomerSex();

        BedDetails bedDetails = BeanUtil.copyProperties(bedDetailsUpdateRequest, BedDetails.class);
        Customer customers = customerMapper.selectOne(new LambdaQueryWrapper<Customer>()
                .eq(Customer::getCustomerName, customerName)
        );
        bedDetails.setCustomerId(customers.getId());
        customerMapper.update(
                new LambdaUpdateWrapper<Customer>()
                        .set(Customer::getCustomerSex, customerSex)
                        .eq(Customer::getCustomerName, customerName)
        );
        // 根据 id 更新
        updateById(bedDetails);

        return R.success("成功");
    }

    /**
     * 床位互换
     *
     * @param bedChangeUpdateRequest
     * @return
     */
    @Override
    @Transactional
    public BaseResponse<String> changeBed(BedChangeUpdateRequest bedChangeUpdateRequest) {
        // 获取A床位信息
        BedDetails ABedDetails = lambdaQuery().eq(BedDetails::getId, bedChangeUpdateRequest.getId()).one();
        //根据新床位号找床位状态
        Bed bed = bedMapper.selectOne(new LambdaQueryWrapper<Bed>().eq(Bed::getBedNo, bedChangeUpdateRequest.getNewBedNo()));
        if(bed.getStatus()==2){
            System.out.println("一");
            Bed bed1 = bedMapper.selectOne(new LambdaQueryWrapper<Bed>().eq(Bed::getId, ABedDetails.getBedId()));
            bed1.setStatus(BedStatus.VACANCY.getCode());
            bedMapper.updateById(bed1);
            BedDetails bedDetails1= BeanUtil.copyProperties(ABedDetails,BedDetails.class);
            bedDetails1.setBedId(bed.getId());
            bedDetails1.setBedDetails("606#" + bed.getBedNo());
            bed.setStatus(BedStatus.OCCUPATION.getCode());
            bedMapper.updateById(bed);
            updateById(bedDetails1);
        }
        else{
            System.out.println("二");
            Integer id = ABedDetails.getId();
            Date startDate = ABedDetails.getStartDate();
            Date endDate = ABedDetails.getEndDate();
            Integer bedId = ABedDetails.getBedId();
            // 根据A床位id获取对应的床位
            Bed ABed = bedMapper.selectOne(new LambdaQueryWrapper<Bed>()
                    .eq(Bed::getId, bedId));
            // B 床位信息
            String BCustomerName = bedChangeUpdateRequest.getCustomerName();
            String BBedNo = bedChangeUpdateRequest.getNewBedNo();
            // 根据B床位号获取对应的床位 id
            Bed BBed = bedMapper.selectOne(new LambdaQueryWrapper<Bed>()
                    .eq(Bed::getBedNo, BBedNo));
            // B床位信息详情
            BedDetails BBedDetails = lambdaQuery()
                    .eq(BedDetails::getBedId, BBed.getId())
                    .one();
            BedDetails newABedDetails = BeanUtil.copyProperties(bedChangeUpdateRequest, BedDetails.class);
            newABedDetails.setBedId(BBedDetails.getBedId());
            newABedDetails.setBedDetails("606#" + BBedNo);
            newABedDetails.setStartDate(BBedDetails.getStartDate());
            newABedDetails.setEndDate(BBedDetails.getEndDate());
            // 更新A床位信息
            updateById(newABedDetails);
            BBedDetails.setStartDate(startDate);
            BBedDetails.setEndDate(endDate);
            BBedDetails.setBedId(bedId);
            BBedDetails.setBedDetails("606#" + ABed.getBedNo());
            updateById(BBedDetails);
        }
        return R.success("床位调换成功");
    }

    /**
     * 删除历史床位信息
     *
     * @param deleteRequest
     * @return
     */
    @Override
    public BaseResponse<String> deleteBedDetails(DeleteRequestBody deleteRequest) {
        if (deleteRequest.getIds().size() == 1) {
            this.baseMapper.deleteById(deleteRequest.getIds().get(0));
        } else {
            this.baseMapper.deleteBatchIds(deleteRequest.getIds());
        }
        return R.success("删除成功");
    }

    /**
     * 通过部分客户姓名获取客户id（模糊查询）
     *
     * @param customerName
     * @return
     */
    private List<Integer> getCustomerIdsByName(String customerName, Integer sex) {
        List<Integer> customers;
        customers = customerMapper.selectList(
                new LambdaQueryWrapper<Customer>()
                        .select(Customer::getId)
                        .like(Customer::getCustomerName, customerName)
                        .eq(sex != null, Customer::getCustomerSex, sex)
        ).stream().map(Customer::getId).toList();
        return customers;
    }

    /**
     * 获取Page<BedDetails> 并转为 Page<BedDetailsVO>
     *
     * @param current
     * @param pageSize
     * @param queryWrapper
     * @return
     */
    private Page<BedDetailsVO> getBedDetailsVOPage(int current, int pageSize, QueryWrapper<BedDetails> queryWrapper) {
        Page<BedDetails> page = page(new Page<>(current, pageSize), queryWrapper);
        Page<BedDetailsVO> roomDetailsVOPage = new Page<>();
        BeanUtil.copyProperties(page, roomDetailsVOPage);
        roomDetailsVOPage.setRecords(page.getRecords().stream().map(this::getBedDetailsVO).toList());
        return roomDetailsVOPage;
    }

    /**
     * BedDetails 转换成 BedDetailsVO
     *
     * @param bedDetails
     * @return
     */
    private BedDetailsVO getBedDetailsVO(BedDetails bedDetails) {
        BedDetailsVO bedDetailsVO = BeanUtil.copyProperties(bedDetails, BedDetailsVO.class);
        Optional.ofNullable(bedDetails.getCustomerId())
                .ifPresent(customerId -> {
                    Customer customer = customerMapper.selectOne(
                            new LambdaQueryWrapper<Customer>()
                                    .eq(Customer::getId, customerId)
                    );
                    bedDetailsVO.setCustomer(customer.getCustomerName());
                    bedDetailsVO.setSex(customer.getCustomerSex());
                });
        System.out.println(bedDetails);
        return bedDetailsVO;
    }
}




