package com.controller.RController;

import com.common.api.Action;
import com.common.api.CommonResult;
import com.pojo.vo.LoginVo;
import com.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "登录接口")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ApiOperation("用户登录")
    @Action(description = "用户登录")
    @PostMapping("/login")
    public CommonResult login(@Validated @RequestBody LoginVo user, BindingResult result){

        //判断是否为空
        if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }

        Map<String, Object> loginMap = loginService.login(user);

        //如果用户密码对比成功
        if ((Boolean) loginMap.get("flag")) {
            return CommonResult.success(loginMap.get("user_cookie"));
        }
        return CommonResult.validateFailed("用户名密码错误");

    }


}
