package com.example.ordertaker.controller;

import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.entity.Administer;
import com.example.ordertaker.entity.Blacklist;
import com.example.ordertaker.entity.Courier;
import com.example.ordertaker.service.AdministerService;
import com.example.ordertaker.service.BlacklistService;
import com.example.ordertaker.service.CourierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "管理员接口", description = "/api/administer")
@RestController
@RequestMapping("/api/administer")
public class AdministerController {

    @Autowired
    private AdministerService administerService;

    @Autowired
    private CourierService courierService;

    @Autowired
    private BlacklistService blacklistService;

    /**
     * 管理员登录
     *
     * @param adminName 管理员用户名
     * @param password  管理员密码
     * @return 登录成功返回管理员id  登录失败返回报错信息
     */
    @Parameters({
            @Parameter(name = "adminName", description = "管理员用户名", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "password", description = "管理员密码", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "管理员登录", description = "管理员登录")
    @GetMapping("/login")
    public long login(@RequestParam String adminName, @RequestParam String password) {
        Administer result = administerService.checkAdministerExist(adminName, password);
        if (result == null) {
            throw new MyException("管理员用户名或者密码错误！");
        }
        return result.getId();
    }

    /**
     * 封禁骑手
     *
     * @param courier 骑手信息，需填写id
     * @return 是否封禁成功
     */
    @Parameter(name = "id", description = "骑手主键id", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "封禁骑手", description = "封禁骑手")
    @RequestMapping(value = "/block", method = {RequestMethod.POST, RequestMethod.PATCH})
    public boolean block(@RequestBody Courier courier) {
        return courierService.block(courier.getId());
    }

    /**
     * 解封骑手
     *
     * @param id 骑手主键id
     * @return 是否解封成功
     */
    @Parameter(name = "id", description = "骑手主键id", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "解封骑手", description = "解封骑手")
    @DeleteMapping("/unblock")
    public boolean unblock(@RequestParam Long id) {
        return courierService.unblock(id);
    }

    /**
     * 设置骑手封禁次数
     *
     * @param courier 骑手信息，需填写id和blockTimes
     * @return 是否设置成功
     */
    @Parameter(name = "courier", description = "骑手信息，需填写id和blockTimes", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "设置骑手封禁次数", description = "设置骑手封禁次数")
    @PatchMapping("/block-times")
    public boolean setBlockTimes(@RequestBody Courier courier) {
        return courierService.setBlockTimes(courier.getId(), courier.getBlockTimes());
    }

    /**
     * 设置骑手评分
     *
     * @param courier 骑手信息，需填写id和rating
     * @return 是否设置成功
     */
    @Parameter(name = "courier", description = "骑手信息，需填写id和rating", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "设置骑手评分", description = "设置骑手评分")
    @PatchMapping("/rating")
    public boolean setRating(@RequestBody Courier courier) {
        return courierService.setRating(courier.getId(), courier.getRating());
    }

    /**
     * 查询骑手列表。可根据姓名查询，或按照违规次数或评分排序
     *
     * @param current     当前页码
     * @param size        每页大小
     * @param courierName 骑手信息，需填写姓名
     * @param order       排序字段，可选值为 {@code rating} 和 {@code blockTimes}
     * @param type        排序类型，可选值为 {@code asc} 和 {@code desc}
     * @return 骑手列表
     */
    @Parameters({
            @Parameter(name = "current", description = "当前页码", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "courierName", description = "骑手信息，需填写姓名", in = ParameterIn.QUERY),
            @Parameter(name = "order", description = "排序字段，字符串，可选值为 \"rating\" 和 \"blockTimes\"", in = ParameterIn.QUERY),
            @Parameter(name = "type", description = "排序类型，可选值为 \"asc\" 和 \"desc\"", in = ParameterIn.QUERY)
    })
    @Operation(summary = "查询骑手列表", description = "查询骑手列表。可根据姓名查询，或按照违规次数或评分排序")
    @GetMapping("/couriers")
    public List<Courier> getCouriers(@RequestParam(defaultValue = "1") long current,
                                     @RequestParam(defaultValue = "10") long size,
                                     String courierName,
                                     String order,
                                     String type) {
        if (order == null && type == null)
            return courierService.page(current, size);

        assert (order != null && type != null);

        if (order.equals("rating") && type.equals("asc"))
            return courierService.pageOrderByRatingAsc(current, size);
        if (order.equals("rating") && type.equals("desc"))
            return courierService.pageOrderByRatingDesc(current, size);
        if (order.equals("blockTimes") && type.equals("asc"))
            return courierService.pageOrderByBlockTimesAsc(current, size);
        if (order.equals("blockTimes") && type.equals("desc"))
            return courierService.pageOrderByBlockTimesDesc(current, size);

        if (courierName == null || courierName.isEmpty())
            throw new MyException("检索骑手姓名为空！");
        return courierService.page(courierName, current, size);
    }

    /**
     * 查询骑手数量
     *
     * @return 骑手数量
     */
    @Operation(summary = "查询骑手数量", description = "查询骑手数量")
    @GetMapping("/count/couriers")
    public long getCouriersCount() {
        return courierService.count();
    }


    /**
     * 查询黑名单列表。可根据骑手姓名查询，或按照创建时间排序
     *
     * @param current     当前页码
     * @param size        每页大小
     * @param courierName 骑手姓名
     * @param order       排序字段，可选值为 {@code createdTime}
     * @param type        排序类型，可选值为 {@code asc} 和 {@code desc}
     * @return 黑名单列表
     */
    @Parameters({
            @Parameter(name = "current", description = "当前页码", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "courierName", description = "骑手姓名", in = ParameterIn.QUERY),
            @Parameter(name = "order", description = "排序字段，可选值为 \"createdTime\"", in = ParameterIn.QUERY),
            @Parameter(name = "type", description = "排序类型，可选值为 \"asc\" 和 \"desc\"", in = ParameterIn.QUERY)
    })
    @Operation(summary = "查询黑名单列表。可根据骑手姓名查询，或按照创建时间排序", description = "查询黑名单列表。可根据骑手姓名查询，或按照创建时间排序")
    @GetMapping("/blacklists")
    public List<Blacklist> getBlacklists(@RequestParam(defaultValue = "1") long current,
                                         @RequestParam(defaultValue = "10") long size,
                                         String courierName,
                                         String order,
                                         String type) {
        if (order == null && type == null)
            return blacklistService.page(current, size);

        assert (order != null && type != null);

        if (order.equals("createdTime") && type.equals("asc"))
            return blacklistService.pageOrderByCreatedTimeAsc(current, size);
        if (order.equals("createdTime") && type.equals("desc"))
            return blacklistService.pageOrderByCreatedTimeDesc(current, size);

        assert (courierName != null);
        return blacklistService.page(courierName, current, size);
    }

    /**
     * 查询黑名单内当前骑手数量
     *
     * @return 黑名单内当前骑手数量
     */
    @Operation(summary = "查询黑名单内当前骑手数量", description = "查询黑名单内当前骑手数量")
    @GetMapping("/count/blacklists")
    public long getBlacklistsCount() {
        return blacklistService.count();
    }
}