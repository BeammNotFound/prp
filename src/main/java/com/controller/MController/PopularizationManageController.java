package com.controller.MController;

import com.common.api.Action;
import com.common.api.CommonResult;
import com.pojo.Popularizations;
import com.service.PopularizationsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author BeamStark
 * @Version 0.1 2020/12
 */
//@RestController
//@Api(tags = "后台科普接口")
public class PopularizationManageController {

    @Autowired
    private PopularizationsService service;

    @ApiOperation("创建科普信息")
    @Action(description = "创建科普信息")
    @PostMapping("/createPopularization")
    public CommonResult createPopularization(@Validated @RequestBody Popularizations popularizations, BindingResult result) {

        if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }

        service.createPopularization(popularizations);

        return CommonResult.success("创建成功，标题为：" + popularizations.getP_title());

    }

    @ApiOperation("通过标题或者文章内容模糊查询科普内容")
    @Action(description = "通过标题或者文章内容模糊查询科普内容")
    @PostMapping("/fuzzyQueryPopularizations")
    public CommonResult fuzzyQueryPopularizations(@ApiParam("输入要查询的标题或者资讯内容") @RequestBody Popularizations popularizations) {

        List<Popularizations> list = service.fuzzyQueryPopularizations(popularizations);

        if (null == list || list.size() == 0) {
            return CommonResult.success("查不到内容呢 QAQ ,检查一下搜索内容~");
        }
        return CommonResult.success(list);

    }
}
