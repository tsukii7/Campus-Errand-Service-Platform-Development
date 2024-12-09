package com.example.ordertaker.controller;

import com.example.ordertaker.entity.Courier;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.service.CourierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "骑手接口", description = "/api/courier")
@RestController
@RequestMapping("/api/courier")
public class CourierController {

    @Autowired
    private CourierService courierService;

    /**
     * 骑手登录
     *
     * @param email    骑手邮箱
     * @param password 骑手密码
     * @return 骑手所有信息
     */
    @Parameters({
            @Parameter(name = "email", description = "骑手邮箱", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "password", description = "骑手密码", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "骑手登录", description = "骑手登录")
    @GetMapping("/login")
    public Courier login(@RequestParam String email, @RequestParam String password) {
        Courier courier = courierService.selectOne(email, password);
        if (courier == null) {
            throw new MyException("邮箱或密码错误");
        }
        return courier;
    }

    /**
     * 骑手注册
     *
     * @param courier 骑手 courier，必须有 courierName, email, password
     * @return 是否注册成功
     */
    @Operation(summary = "骑手注册", description = "骑手注册")
    @Parameter(name = "courier", description = "骑手信息", in = ParameterIn.QUERY, required = true)
    @PostMapping("/register")
    public boolean register(@RequestBody Courier courier) {
        return courierService.save(courier);
    }

    /**
     * 判断骑手是否被封禁
     *
     * @param id 骑手主键id
     * @return 是否被封禁
     */
    @Parameter(name = "id", description = "骑手主键id", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "判断骑手是否被封禁", description = "判断骑手是否被封禁")
    @GetMapping("/check-block")
    public boolean isBlock(@RequestParam Long id) {
        return courierService.isBlock(id);
    }

    /**
     * 通过id获取骑手信息
     *
     * @param id 骑手id
     * @return 骑手
     */
    @Parameter(name = "id", description = "骑手主键id", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "通过id获取骑手信息", description = "通过id获取骑手信息")
    @GetMapping("/get")
    public Courier get(@RequestParam Long id) {
        return courierService.getById(id);
    }

    /**
     * 更新骑手信息
     *
     * @param courier 骑手信息
     * @return 是否更新成功
     */
    @Operation(summary = "更新骑手信息", description = "更新骑手信息")
    @Parameter(name = "courier", description = "骑手信息", in = ParameterIn.QUERY, required = true)
    @PatchMapping("/update")
    public boolean update(@RequestBody Courier courier) {
        return courierService.updateById(courier);
    }
}
