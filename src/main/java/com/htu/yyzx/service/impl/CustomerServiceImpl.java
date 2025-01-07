package com.htu.yyzx.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.*;
import com.htu.yyzx.mapper.*;
import com.htu.yyzx.model.dto.CustomerNurse.CustomerNurseQueryRequest;
import com.htu.yyzx.model.dto.CustomerNurse.CustomerNurseUpdateRequest;
import com.htu.yyzx.model.dto.HealthSereviceS.HealthAdminAddRequest;
import com.htu.yyzx.model.dto.HealthSereviceS.HealthServiceSQueryRequets;
import com.htu.yyzx.model.dto.customer.CheckInAddRequest;
import com.htu.yyzx.model.dto.customer.CheckInQueryRequest;
import com.htu.yyzx.model.dto.customer.CheckOutQueryRequest;
import com.htu.yyzx.model.dto.customer.CustomerDetailsQueryRequest;
import com.htu.yyzx.model.entity.*;
import com.htu.yyzx.model.enums.BedStatus;
import com.htu.yyzx.model.enums.UserRoleStatus;
import com.htu.yyzx.model.vo.CheckInVO;
import com.htu.yyzx.model.vo.CustomerDetailsVO;
import com.htu.yyzx.model.vo.CustomerNurseVO;
import com.htu.yyzx.model.vo.NoPreferenceVO;
import com.htu.yyzx.service.CustomerService;
import com.htu.yyzx.utils.MaskUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
        implements CustomerService {

    private final BedMapper bedMapper;
    private final BedDetailsMapper bedDetailsMapper;
    private final UserMapper userMapper;
    private final NurseLevelMapper nurseLevelMapper;
    private final BackDownMapper backDownMapper;
    private final OutwardMapper outwardMapper;
    private final CustomerPreferenceMapper customerPreferenceMapper;
    private final NurseRecordMapper nurseRecordMapper;

    /**
     * 分页查询入住登记表
     *
     * @param checkInQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<CheckInVO>> queryCheckIn(CheckInQueryRequest checkInQueryRequest) {
        String oldStatus = checkInQueryRequest.getOldStatus();
        String customerName = checkInQueryRequest.getCustomerName();
        Integer customerSex = checkInQueryRequest.getCustomerSex();
        String idcard = checkInQueryRequest.getIdcard();
        String roomNo = checkInQueryRequest.getRoomNo();
        Date checkinDate = checkInQueryRequest.getCheckinDate();
        Date expirationDate = checkInQueryRequest.getExpirationDate();
        String contactTel = checkInQueryRequest.getContactTel();
        int current = checkInQueryRequest.getCurrent();
        int pageSize = checkInQueryRequest.getPageSize();
        String sortField = checkInQueryRequest.getSortField();
        String sortOrder = checkInQueryRequest.getSortOrder();


        LambdaQueryWrapper<Customer> wrapper = getCheckInLambdaQuery(customerName, customerSex, idcard, roomNo, checkinDate, expirationDate, contactTel, oldStatus);

        Page<Customer> page = page(new Page<>(current, pageSize), wrapper);
        Page<CheckInVO> checkInVO = new Page<>(current, pageSize, page.getTotal());
        checkInVO.setRecords(page.getRecords().stream().map(this::toCheckInVO).collect(Collectors.toList()));
        return R.success(checkInVO);
    }

    /**
     * 删除入住登记表
     *
     * @param deleteRequest
     * @return
     */
    @Override
    @Transactional
    public BaseResponse<String> deleteCheckIn(DeleteRequestBody deleteRequest) {
        int size = deleteRequest.getIds().size();
        if (size == 1) {
            Long id = deleteRequest.getIds().get(0);
            Customer one = lambdaQuery().eq(Customer::getId, id).one();
            Integer bedId = one.getBedId(); // 获取床号
            bedMapper.update(new LambdaUpdateWrapper<Bed>()
                    .set(Bed::getStatus, BedStatus.VACANCY.getCode())
                    .eq(Bed::getId, bedId)); // 修改空闲床位状态
            bedDetailsMapper.delete(new LambdaUpdateWrapper<BedDetails>()
                    .eq(BedDetails::getBedId, bedId)); // 删除床位详情
            removeById(id);
        } else {
            this.baseMapper.deleteBatchIds(deleteRequest.getIds());
        }
        return R.success("删除成功");
    }

    /**
     * 添加入住登记表
     *
     * @param checkInAddRequest
     * @return
     */
    @Override
    @Transactional
    public BaseResponse<String> addCheckIn(CheckInAddRequest checkInAddRequest) {

        String bedNo = checkInAddRequest.getBedNo();
        String buildingNo = checkInAddRequest.getBuildingNo();
        Date checkinDate = checkInAddRequest.getCheckinDate();
        Date expirationDate = checkInAddRequest.getExpirationDate();

        String username = checkInAddRequest.getUsername();
        String levelName = checkInAddRequest.getLevelName();

        Customer newCustomer = BeanUtil.copyProperties(checkInAddRequest, Customer.class);

        Date date = new Date();

        // 判断入住日期是否合法，如果入住日期小于当前日期返回失败
        // if (DateUtil.compare(checkinDate, DateUtil.date().toDateStr()) <= 0) {
        //     return R.error(ErrorCode.PARAMS_ERROR, "入住日期不能小于当前日期");
        // }
        // 判断合同到期时间是否大于入住日期，如果小于等于返回失败
        if (expirationDate.before(checkinDate)) {
            return R.error(ErrorCode.PARAMS_ERROR, "合同到期时间不能小于入住日期");
        }

        Bed bed = bedMapper.selectOne(new LambdaQueryWrapper<Bed>()
                .eq(Bed::getBedNo, bedNo));
        // 判断床号是否被占用，如果被占用返回失败
        if (bed.getStatus() == BedStatus.OCCUPATION.getCode()) {
            //     被占用
            return R.error(ErrorCode.PARAMS_ERROR, "床位被占用");
        }

        // 修改空闲床位被占用
        int update = bedMapper.update(new LambdaUpdateWrapper<Bed>()
                .set(Bed::getStatus, BedStatus.OCCUPATION.getCode())
                .eq(Bed::getBedNo, bedNo));

        // 判断 是否存在管家，如果不存在将customer userId 赋值为 -1
        if (StrUtil.isBlank(username)) {
            newCustomer.setBedId(-1);
        } else {
            // 存在
            Optional.ofNullable(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getNickname, username)))
                    .ifPresent(user -> newCustomer.setUserId(user.getId()));
        }

        // 判断是否存在护理等级，如果不存在将customer levelId 赋值为 null，否则赋值为护理等级id
        if (StrUtil.isNotBlank(levelName)) {
            NurseLevel nurseLevel = nurseLevelMapper.selectOne(new LambdaQueryWrapper<NurseLevel>().eq(NurseLevel::getLevelName, levelName));
            newCustomer.setLevelId(nurseLevel.getId());
        } else {
            newCustomer.setLevelId(null);
        }
        // 床位没有被占用, newCustomer 设置 bedId
        newCustomer.setBedId(bed.getId());

        // 将 newCustomer 插入数据库
        baseMapper.insert(newCustomer);

        // 添加床位详情
        BedDetails bedDetails = BedDetails.builder()
                .customerId(newCustomer.getId())
                .bedDetails(buildingNo + "#" + bedNo)
                .bedId(bed.getId())
                .startDate(checkinDate)
                .endDate(expirationDate)
                .build();
        bedDetailsMapper.insert(bedDetails);
        return R.success("登记成功");
    }


    /**
     * Customer to CheckInVO (包含数据脱敏)
     *
     * @param customer
     * @return
     */
    private CheckInVO toCheckInVO(Customer customer) {
        CheckInVO checkInVO = BeanUtil.copyProperties(customer, CheckInVO.class);
        Integer bedId = customer.getBedId();
        Integer userId = customer.getUserId();
        checkInVO.setIdcard(MaskUtils.idCardNum(customer.getIdcard()));
        checkInVO.setContactTel(MaskUtils.mobilePhone(customer.getContactTel()));
        if (userId != -1) {
            Optional.ofNullable(userMapper.selectById(userId))
                    .ifPresent(user -> checkInVO.setUsername(user.getNickname()));
        } else {
            checkInVO.setUsername("");
        }

        Optional.ofNullable(bedMapper.selectById(bedId))
                .ifPresent(bed -> checkInVO.setBedNo(bed.getBedNo()));
        return checkInVO;
    }

    /**
     * 入住登记表查询条件构造器
     *
     * @param customerName
     * @param customerSex
     * @param idcard
     * @param roomNo
     * @param checkinDate
     * @param expirationDate
     * @param contactTel
     * @return
     */
    private static LambdaQueryWrapper<Customer> getCheckInLambdaQuery(String customerName, Integer customerSex, String idcard, String roomNo, Date checkinDate, Date expirationDate, String contactTel, String oldStatus) {
        LambdaQueryWrapper<Customer> customerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        customerLambdaQueryWrapper
                .like(StrUtil.isNotBlank(customerName), Customer::getCustomerName, customerName)
                .eq(customerSex != null, Customer::getCustomerSex, customerSex)
                .like(StrUtil.isNotBlank(idcard), Customer::getIdcard, idcard)
                .like(StrUtil.isNotBlank(roomNo), Customer::getRoomNo, roomNo)
                .gt(checkinDate != null, Customer::getCheckinDate, checkinDate)
                .lt(expirationDate != null, Customer::getExpirationDate, expirationDate)
                .like(StrUtil.isNotBlank(contactTel), Customer::getContactTel, contactTel);

        if ("CAN".equals(oldStatus)) {
            customerLambdaQueryWrapper.eq(Customer::getUserId, -1);
        } else {
            customerLambdaQueryWrapper.gt(Customer::getUserId, -1);
        }

        return customerLambdaQueryWrapper;
    }

    /**
     * 获取退房客户详情
     *
     * @param customerDetailsQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<CustomerDetailsVO>> queryCustomerDetails(CustomerDetailsQueryRequest customerDetailsQueryRequest) {

        String customerName = customerDetailsQueryRequest.getCustomerName();
        int current = customerDetailsQueryRequest.getCurrent();
        int pageSize = customerDetailsQueryRequest.getPageSize();
        String sortField = customerDetailsQueryRequest.getSortField();
        String sortOrder = customerDetailsQueryRequest.getSortOrder();
        LambdaQueryWrapper<Customer> customerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 获取有退住记录的 customerId
        List<Integer> customerIds = backDownMapper.selectList(new LambdaQueryWrapper<BackDown>()
                .select(BackDown::getCustomerId)).stream().map(BackDown::getCustomerId).toList();

        int loginIdAsInt = StpUtil.getLoginIdAsInt();
        Integer roleId = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, loginIdAsInt)).getRoleId();

        Page<Customer> page = page(new Page<>(current, pageSize), customerLambdaQueryWrapper
                .in(Customer::getId, customerIds)
                .eq(roleId == UserRoleStatus.BUTLER.getCode(), Customer::getUserId, loginIdAsInt)
                .like(StrUtil.isNotBlank(customerName), Customer::getCustomerName, customerName));

        Page<CustomerDetailsVO> customerDetailsVOPage = new Page<>(current, pageSize, page.getTotal());
        customerDetailsVOPage.setRecords(page.getRecords().stream().map(this::getCustomerDetailsVO).toList());
        return R.success(customerDetailsVOPage);
    }

    /**
     * 获取外出房客户详情
     *
     * @param customerDetailsQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<CustomerDetailsVO>> queryOutwardCustomer(CustomerDetailsQueryRequest customerDetailsQueryRequest) {
        String customerName = customerDetailsQueryRequest.getCustomerName();
        int current = customerDetailsQueryRequest.getCurrent();
        int pageSize = customerDetailsQueryRequest.getPageSize();
        String sortField = customerDetailsQueryRequest.getSortField();
        String sortOrder = customerDetailsQueryRequest.getSortOrder();

        LambdaQueryWrapper<Customer> customerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 获取有退住记录的 customerId
        List<Integer> customerIds = outwardMapper.selectList(new LambdaQueryWrapper<Outward>()
                .select(Outward::getCustomerId)).stream().map(Outward::getCustomerId).toList();

        int loginIdAsInt = StpUtil.getLoginIdAsInt(); // 当前登录用户的 Id
        Integer roleId = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, loginIdAsInt)).getRoleId();

        Page<Customer> page = page(new Page<>(current, pageSize), customerLambdaQueryWrapper
                .in(Customer::getId, customerIds)
                // 如果是管家，则只能查询自己负责的客户
                .eq(roleId == UserRoleStatus.BUTLER.getCode(), Customer::getUserId, loginIdAsInt)
                .like(StrUtil.isNotBlank(customerName), Customer::getCustomerName, customerName));
        Page<CustomerDetailsVO> customerDetailsVOPage = new Page<>(current, pageSize, page.getTotal());
        customerDetailsVOPage.setRecords(page.getRecords().stream().map(this::getCustomerDetailsVO).toList());
        return R.success(customerDetailsVOPage);
    }

    /**
     * 模糊查询用户姓名列表
     *
     * @param checkOutQueryRequest
     * @return
     */
    @Override
    public BaseResponse<List<String>> queryCustomerName(CheckOutQueryRequest checkOutQueryRequest) {
        int loginIdAsInt = StpUtil.getLoginIdAsInt();
        Integer roleId = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, loginIdAsInt)).getRoleId();
        List<String> list = lambdaQuery()
                .select(Customer::getCustomerName)
                .like(StrUtil.isNotBlank(checkOutQueryRequest.getCustomerName()), Customer::getCustomerName, checkOutQueryRequest.getCustomerName())
                .eq(roleId == UserRoleStatus.BUTLER.getCode(), Customer::getUserId, loginIdAsInt)
                .list()
                .stream()
                .map(Customer::getCustomerName)
                .toList();
        return R.success(list);
    }

    /**
     * 获取无膳食偏好顾客
     *
     * @param customerName
     * @return
     */
    @Override
    public BaseResponse<NoPreferenceVO> queryCustomerOfNoPreference(String customerName) {
        List<Integer> hasPreferenceIds = customerPreferenceMapper.selectList(new LambdaQueryWrapper<>()).stream()
                .map(CustomerPreference::getCustomerId).toList();
        // 获取无膳食偏好的顾客
        List<Customer> noPreference = lambdaQuery()
                .notIn(Customer::getId, hasPreferenceIds)
                .like(StrUtil.isNotBlank(customerName), Customer::getCustomerName, customerName)
                .list();
        NoPreferenceVO noPreferenceVO = new NoPreferenceVO();
        noPreferenceVO.setCustomers(noPreference);
        return R.success(noPreferenceVO, "查询成功");
    }

    /**
     * 分页查询客户-房间-护理等级
     *
     * @param customerNurseQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<CustomerNurseVO>> queryCustomerNurse(CustomerNurseQueryRequest customerNurseQueryRequest) {
        String customerName = customerNurseQueryRequest.getCustomerName();
        Boolean flag = customerNurseQueryRequest.getFlag();
        int current = customerNurseQueryRequest.getCurrent();
        int pageSize = customerNurseQueryRequest.getPageSize();
        String sortField = customerNurseQueryRequest.getSortField();
        String sortOrder = customerNurseQueryRequest.getSortOrder();
        // 获取当前登录用户id
        int loginIdAsInt = StpUtil.getLoginIdAsInt();
        // 获取当前登录用户角色
        Integer roleId = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, loginIdAsInt)).getRoleId();
        List<Integer> list = null;
        if (flag) {
            list = nurseRecordMapper.selectList(new LambdaQueryWrapper<NurseRecord>()).stream().map(NurseRecord::getCustomerId).toList();
        }

        Page<Customer> page = page(new Page<>(current, pageSize), new LambdaQueryWrapper<Customer>()
                .like(StrUtil.isNotBlank(customerName), Customer::getCustomerName, customerName)
                .eq(roleId == UserRoleStatus.BUTLER.getCode(), Customer::getUserId, loginIdAsInt)
                .in(flag, Customer::getId, list)
        );
        Page<CustomerNurseVO> customerNurseVOPage = new Page<>(current, pageSize, page.getTotal());
        customerNurseVOPage.setRecords(page.getRecords().stream().map(this::getCustomerNurseVO).toList());
        return R.success(customerNurseVOPage, "查询成功");
    }

    /**
     * customer 对象转为 CustomerNurseVO 对象
     *
     * @param customer
     * @return
     */
    @NotNull
    private CustomerNurseVO getCustomerNurseVO(Customer customer) {
        CustomerNurseVO customerNurseVO = BeanUtil.copyProperties(customer, CustomerNurseVO.class);
        Optional.ofNullable(customer.getLevelId())
                .ifPresent(levelId -> customerNurseVO.setLevelName(nurseLevelMapper.selectById(levelId).getLevelName()));
        customerNurseVO.setBedNo(bedMapper.selectById(customer.getBedId()).getBedNo());
        return customerNurseVO;
    }

    @Override
    public BaseResponse<Page<CustomerNurseVO>> updateCustomerNurse(CustomerNurseUpdateRequest customerNurseUpdateRequest) {
        Customer customer = BeanUtil.copyProperties(customerNurseUpdateRequest, Customer.class);
        Optional.ofNullable(customerNurseUpdateRequest.getLevelName())
                .ifPresent(levelName -> customer.setLevelId(nurseLevelMapper.selectOne(new LambdaQueryWrapper<NurseLevel>()
                        .eq(NurseLevel::getLevelName, levelName)).getId()));
        lambdaUpdate()
                .set(Customer::getLevelId, customer.getLevelId())
                .eq(Customer::getId, customer.getId())
                .update();
        return R.success(null, "修改成功");
    }

    /**
     * 分页查询指定用户服务的客户
     *
     * @param healthServiceSQueryRequets
     * @return
     */
    @Override
    public BaseResponse<Page<CustomerDetailsVO>> queryCustomerByHealthAdmin(HealthServiceSQueryRequets healthServiceSQueryRequets) {
        Integer userId = healthServiceSQueryRequets.getUserId();
        int current = healthServiceSQueryRequets.getCurrent();
        int pageSize = healthServiceSQueryRequets.getPageSize();
        String sortField = healthServiceSQueryRequets.getSortField();
        String sortOrder = healthServiceSQueryRequets.getSortOrder();
        Page<Customer> page = page(new Page<>(current, pageSize), new LambdaQueryWrapper<Customer>()
                .eq(Customer::getUserId, userId));
        Page<CustomerDetailsVO> customerDetailsVOPage = new Page<>(current, pageSize, page.getTotal());
        customerDetailsVOPage.setRecords(page.getRecords().stream().map(this::getCustomerDetailsVO).collect(Collectors.toList()));
        return R.success(customerDetailsVOPage, "查询成功");
    }

    @Override
    public BaseResponse<String> deleteServiceS(DeleteRequestBody deleteRequestBody) {
        deleteRequestBody.getIds().forEach(id -> {
            lambdaUpdate()
                    .set(Customer::getUserId, UserRoleStatus.NO_BUTLER.getCode())
                    .eq(Customer::getId, id)
                    .update();
        });
        return R.success(null, "移除成功");
    }

    /**
     * 分页查询未指定健康管家的客户
     *
     * @return
     */
    @Override
    public BaseResponse<Page<CustomerDetailsVO>> queryCustomerByNoHealthAdmin(PageRequest pageRequest) {
        int current = pageRequest.getCurrent();
        int pageSize = pageRequest.getPageSize();
        String sortField = pageRequest.getSortField();
        String sortOrder = pageRequest.getSortOrder();

        Page<Customer> page = page(new Page<>(current, pageSize), new LambdaQueryWrapper<Customer>()
                .eq(Customer::getUserId, UserRoleStatus.NO_BUTLER.getCode()));
        Page<CustomerDetailsVO> customerDetailsVOPage = new Page<>(current, pageSize, page.getTotal());
        customerDetailsVOPage.setRecords(page.getRecords().stream().map(this::getCustomerDetailsVO).collect(Collectors.toList()));
        return R.success(customerDetailsVOPage, "查询成功");
    }

    /**
     * 无健康管家客户指定健康管家
     *
     * @param healthAdminAddRequest
     * @return
     */
    @Override
    public BaseResponse<String> addHealthAdmin(List<HealthAdminAddRequest> healthAdminAddRequest) {
        healthAdminAddRequest.forEach(add -> {
            lambdaUpdate()
                    .set(Customer::getUserId, add.getHealthAdminId())
                    .eq(Customer::getId, add.getCustomerId())
                    .update();
        });
        return R.success(null, "添加成功");
    }

    /**
     * 查询客户列表
     *
     * @param customerNurseQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<CustomerDetailsVO>> queryCustomerList(CustomerNurseQueryRequest customerNurseQueryRequest) {
        String customerName = customerNurseQueryRequest.getCustomerName();
        int current = customerNurseQueryRequest.getCurrent();
        int pageSize = customerNurseQueryRequest.getPageSize();
        String sortField = customerNurseQueryRequest.getSortField();
        String sortOrder = customerNurseQueryRequest.getSortOrder();
        Page<Customer> page = page(new Page<>(current, pageSize), new LambdaQueryWrapper<Customer>()
                .like(StringUtils.isNotBlank(customerName), Customer::getCustomerName, customerName));
        Page<CustomerDetailsVO> customerNurseVOPage = new Page<>(current, pageSize, page.getTotal());
        customerNurseVOPage.setRecords(page.getRecords().stream().map(this::getCustomerDetailsVO).collect(Collectors.toList()));
        return R.success(customerNurseVOPage, "查询成功");
    }

    private CustomerDetailsVO getCustomerDetailsVO(Customer customer) {
        CustomerDetailsVO customerDetailsVO = BeanUtil.copyProperties(customer, CustomerDetailsVO.class);

        Integer bedId = customer.getBedId();
        Integer userId = customer.getUserId();
        Integer levelId = customer.getLevelId();
        Optional.ofNullable(bedMapper.selectOne(new LambdaQueryWrapper<Bed>()
                .eq(Bed::getId, bedId)
        )).ifPresent(bed -> customerDetailsVO.setBedNo(bed.getBedNo()));
        Optional.ofNullable(
                nurseLevelMapper.selectOne(
                        new LambdaQueryWrapper<NurseLevel>()
                                .eq(NurseLevel::getId, levelId)
                )
        ).ifPresent(nurseLevel -> customerDetailsVO.setLevelName(nurseLevel.getLevelName()));
        return customerDetailsVO;
    }
}




