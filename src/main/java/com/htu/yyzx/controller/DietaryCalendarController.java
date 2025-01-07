package com.htu.yyzx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.R;
import com.htu.yyzx.model.entity.Dietary;
import com.htu.yyzx.model.entity.DietaryCalerdar;
import com.htu.yyzx.model.vo.DietaryCalerdarVo;
import com.htu.yyzx.service.DietaryCalerdarService;
import com.htu.yyzx.service.DietaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/meal")
@RestController
public class DietaryCalendarController {
    @Autowired
    private  DietaryCalerdarService dietaryCalerdarService;
    @Autowired
    private DietaryService dietaryService;
    @GetMapping("/selectmeal")
    public BaseResponse SelectMealByCustomerId(Integer customerId){
        BaseResponse<List<DietaryCalerdarVo>> listBaseResponse = dietaryCalerdarService.listByCustomerId(customerId);
        return R.success(listBaseResponse);
    }
    @GetMapping("/getdietaryList")
    public BaseResponse getDietaryList(){
        List<Dietary> list = dietaryService.list(new LambdaQueryWrapper<Dietary>());
        return R.success(list);
    }
    @PostMapping("/adddietaryCalerdar")
    public BaseResponse addDietaryCalerdar(@RequestBody DietaryCalerdar dietaryCalerdar){
        boolean save = dietaryCalerdarService.save(dietaryCalerdar);
        if (save) {
            return R.success(null,"保存数据成功");
        }
        else return R.error(500,"保存数据失败");
    }
    @PutMapping("/changedietarycalerdar")
    public BaseResponse changeDietaryCalerdar(@RequestBody DietaryCalerdar dietaryCalerdar){
        boolean b = dietaryCalerdarService.updateById(dietaryCalerdar);
        if (b) {
            return R.success(null,"更新数据成功");
        }
        else return R.error(500,"更新数据失败");
    }

}
