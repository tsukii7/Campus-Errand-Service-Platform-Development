package com.example.ordertaker.controller;

import com.example.ordertaker.entity.Client;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口", description = "/api/client")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    /**
     * 用户登录
     *
     * @param email    用户邮箱
     * @param password 用户密码
     * @return 登录成功返回用户所有信息  登录失败返回Exception
     */

    @Parameters({
            @Parameter(name = "email", description = "用户邮箱", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "password", description = "用户密码", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "用户登录", description = "用户登录")
    @GetMapping("/login")
    public Client login(@RequestParam String email, @RequestParam String password) {
        Client client = clientService.selectOne(email, password);
        if (client == null) {
            throw new MyException("邮箱或密码错误");
        }
        return client;
    }


    /**
     * 用户注册
     *
     * @param client 用户信息，必须有 clientName, email, passsword
     * @return 注册成功返回用户id  登录失败返回Exception
     */
    @Operation(summary = "用户注册", description = "用户注册")
    @Parameter(name = "client", description = "用户信息", in = ParameterIn.QUERY, required = true)
    @PostMapping("/register")
    public boolean register(@RequestBody Client client) {
        return clientService.save(client);
    }

    /**
     * 查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Operation(summary = "查询用户信息", description = "查询用户信息")
    @Parameter(name = "id", description = "用户ID", in = ParameterIn.QUERY, required = true)
    @GetMapping("/get")
    public Client get(@RequestParam long id) {
        return clientService.getById(id);
    }


    /**
     * 更新用户信息
     *
     * @param client 用户信息
     * @return 更新成功返回true  更新失败返回false
     */
    @Operation(summary = "更新用户信息", description = "更新用户信息")
    @Parameter(name = "client", description = "用户信息", in = ParameterIn.QUERY, required = true)
    @PatchMapping("/update")
    public boolean update(@RequestBody Client client) {
        return clientService.updateById(client);
    }

}



