package com.example.ordertaker.controller;

import com.example.ordertaker.entity.DeliveryAddress;
import com.example.ordertaker.service.DeliveryAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "收货地址接口", description = "/api/deliveryAddress")
@RestController
@RequestMapping("/api/deliveryAddress")
public class DeliveryAddressController {

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    /**
     * 新建收货地址
     *
     * @param deliveryAddress 收货地址，必须有clientId,address,phoneNumber,receiver
     * @return 是否成功插入
     */
    @Operation(summary = "新建收货地址", description = "新建收货地址")
    @Parameter(name = "deliveryAddress", description = "收货地址，必须有clientId,address,phoneNumber,receiver", in = ParameterIn.QUERY, required = true)
    @PostMapping("/add")
    public boolean addDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress) {
        return deliveryAddressService.save(deliveryAddress);
    }

    /**
     * 通过id获取地址
     *
     * @param id 地址id
     * @return 地址
     */
    @Parameter(name = "id", description = "地址id", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "通过id获取地址", description = "通过id获取地址")
    @GetMapping("/get")
    public DeliveryAddress get(@RequestParam Long id) {
        return deliveryAddressService.getById(id);
    }


    /**
     * 通过client的ID获取该client的所有地址
     *
     * @param clientId 用户Id
     * @param current  当前页
     * @param size     每页大小
     * @return 所有地址
     */
    @Parameters({
            @Parameter(name = "clientId", description = "用户Id", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "通过client的ID获取该client的所有地址", description = "通过client的ID获取该client的所有地址")
    @GetMapping("/page")
    public List<DeliveryAddress> page(@RequestParam long clientId,
                                      @RequestParam(defaultValue = "1") long current,
                                      @RequestParam(defaultValue = "10") long size) {
        return deliveryAddressService.page(clientId, current, size);
    }

    /**
     * 通过client的ID获取该client的所有地址的数量
     *
     * @param clientId 用户Id
     * @return 所有地址的数量
     */
    @Parameter(name = "clientId", description = "用户Id", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "通过client的ID获取该client的所有地址的数量", description = "通过client的ID获取该client的所有地址的数量")
    @GetMapping("/count")
    public long count(@RequestParam long clientId) {
        return deliveryAddressService.count(clientId);
    }

    /**
     * 通过id删除地址
     *
     * @param id 地址id
     * @return 是否成功删除
     */
    @Parameter(name = "id", description = "地址id", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "通过id删除地址", description = "通过id删除地址")
    @DeleteMapping("/delete")
    public boolean delete(@RequestParam Long id) {
        return deliveryAddressService.removeById(id);
    }

    /**
     * 更新地址
     *
     * @param deliveryAddress 地址，必须包含主键id
     * @return 是否成功更新
     */
    @Operation(summary = "更新地址", description = "更新地址")
    @Parameter(name = "deliveryAddress", description = "地址，必须包含主键id", in = ParameterIn.QUERY, required = true)
    @PostMapping("/update")
    public boolean update(@RequestBody DeliveryAddress deliveryAddress) {
        return deliveryAddressService.updateById(deliveryAddress);
    }
}

