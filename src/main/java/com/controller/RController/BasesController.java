package com.controller.RController;

import com.common.api.Action;
import com.common.api.CommonResult;
import com.pojo.Bases;
import com.pojo.vo.BaseIdVo;
import com.pojo.vo.QueryBasesVo;
import com.service.BasesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "基地接口")
@RestController
public class BasesController {

    @Autowired
    private BasesService service;

    @ApiOperation("根据基地名模糊搜索基地")
    @Action(description = "根据基地名模糊搜索基地")
    @PostMapping("/fuzzyQueryBases")
    public CommonResult fuzzyQueryBases(@RequestBody QueryBasesVo vo){
        if (vo.getB_name().equals("") || vo.equals(null)) {
            return CommonResult.success(service.queryBases());
        }
        return CommonResult.success(service.fuzzyQueryBases(vo));
    }

    @ApiOperation("查询全部基地资讯")
    @Action(description = "查询全部基地资讯")
    @GetMapping("/queryBases")
    public CommonResult queryBases() {
        return CommonResult.success(service.queryBases());
    }


    @ApiOperation("根据base_id查询基地图片")
    @Action(description = "根据base_id查询基地图片")
    @PostMapping("queryBasesImagesById")
    public CommonResult queryBasesImagesById(@Validated @RequestBody BaseIdVo baseIdVo, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }

        return CommonResult.success(service.queryBasesImagesById(baseIdVo));

    }

    @ApiOperation("根据base_id查询基地资讯")
    @Action(description = "根据base_id查询基地资讯")
    @PostMapping("queryBaseMessagesById")
    public CommonResult queryBaseMessagesById(@Validated @RequestBody BaseIdVo baseIdVo, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }

        return CommonResult.success(service.queryBaseMessages(baseIdVo));
    }

    @ApiOperation("根据base_id查询基地信息")
    @Action(description = "根据base_id查询基地信息")
    @PostMapping("queryBasesById")
    public CommonResult queryBasesById(@Validated @RequestBody BaseIdVo vo, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        return CommonResult.success(service.queryBasesById(vo.getBase_id()));
    }

}
