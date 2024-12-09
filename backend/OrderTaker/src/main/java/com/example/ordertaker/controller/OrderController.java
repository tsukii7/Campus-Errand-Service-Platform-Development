package com.example.ordertaker.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ordertaker.constant.CurrentStatusEnum;
import com.example.ordertaker.entity.Order;
import com.example.ordertaker.entity.Review;
import com.example.ordertaker.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ordertaker.util.Util.date2String;

@Tag(name = "订单接口", description = "/api/order")
@RestController
@RequestMapping("/api/order")
@EnableScheduling
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 定时任务
     * 每一分钟扫描一次Order表更新查看订单是否超时
     */
    @Scheduled(fixedRate = 60000)
    public void checkStatus() {
        // System.out.println("hello world!!!");
        orderService.checkStatus();
    }
    /**
     * 获取所有待接单订单数量
     *
     * @return 待接单订单数量
     */
    @Operation(summary = "获取待接单订单数量", description = "获取待接单订单数量")
    @GetMapping("/count/unaccepted")
    public long countUnacceptedAll() {
        return orderService.count(new LambdaQueryWrapper<Order>()
                .eq(Order::getCurrentStatus, CurrentStatusEnum.PLACED));
    }

    /**
     * 根据客户ID获取已确认订单
     *
     * @param clientId 客户ID
     * @param current  当前页
     * @param size     每页大小
     * @return 已确认订单
     */
    @Parameters({
            @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "根据客户ID获取已确认订单", description = "根据客户ID获取已确认订单")
    @GetMapping("/client/confirmed")
    public Object confirmed(@RequestParam Long clientId,
                            @RequestParam(defaultValue = "1") long current,
                            @RequestParam(defaultValue = "10") long size) {
        return orderService.pageConfirmedByClientId(clientId, current, size);
    }

    /**
     * 获取已完成订单数量
     *
     * @param clientId 客户ID
     * @return 已完成订单数量
     */
    @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "获取已完成订单数量", description = "获取已完成订单数量")
    @GetMapping("/count/client/confirmed")
    public long countFinished(@RequestParam Long clientId) {
        return orderService.count(new LambdaQueryWrapper<Order>()
                .eq(Order::getClientId, clientId)
                .and(wrapper -> wrapper
                        .eq(Order::getCurrentStatus, CurrentStatusEnum.CONFIRMED)
                ));
    }

    /**
     * 根据客户ID获取已取消订单
     *
     * @param clientId 客户ID
     * @param current  当前页
     * @param size     每页大小
     * @return 已取消订单
     */
    @Parameters({
            @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "根据客户ID获取已取消订单", description = "根据客户ID获取已取消订单")
    @GetMapping("/client/cancelled")
    public Object cancelled(@RequestParam Long clientId,
                            @RequestParam(defaultValue = "1") long current,
                            @RequestParam(defaultValue = "10") long size) {
        return orderService.pageCancelledByClientId(clientId, current, size);
    }

    /**
     * 获取已取消订单数量
     *
     * @param clientId 客户ID
     * @return 已取消订单数量
     */
    @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "获取已取消订单数量", description = "获取已取消订单数量")
    @GetMapping("/count/client/cancelled")
    public long countCanceled(@RequestParam Long clientId) {
        return orderService.count(new LambdaQueryWrapper<Order>()
                .eq(Order::getClientId, clientId)
                .and(wrapper -> wrapper
                        .eq(Order::getCurrentStatus, CurrentStatusEnum.CANCELLED)
                ));
    }

    /**
     * 不管状态，根据客户ID获取所有类别的订单
     *
     * @param clientId 客户ID
     * @param current  当前页
     * @param size     每页大小
     * @return 不分类、所有类别的订单
     */
    @Parameters({
            @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "不管状态，根据客户ID获取所有类别的订单", description = "不管状态，根据客户ID获取所有类别的订单")
    @GetMapping("/client/all")
    public List<Order> allByClientId(@RequestParam Long clientId,
                                     @RequestParam(defaultValue = "1") long current,
                                     @RequestParam(defaultValue = "10") long size) {
        return orderService.pageByClientId(clientId, current, size);
    }

    /**
     * 根据客户ID获取订单数量
     *
     * @param clientId 客户ID
     * @return 订单数量
     */
    @Operation(summary = "根据客户ID获取订单数量", description = "根据客户ID获取订单数量")
    @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true)
    @GetMapping("/count/client/all")
    public long countByClientId(@RequestParam Long clientId) {
        return orderService.countByClientId(clientId);
    }

    /**
     * 根据客户ID获取外卖订单
     *
     * @param clientId 客户ID
     * @param current  当前页
     * @param size     每页大小
     * @return 外卖订单
     */
    @Parameters({
            @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "根据客户ID获取外卖订单", description = "根据客户ID获取外卖订单")
    @GetMapping("/client/takeout")
    public List<Order> takeout(@RequestParam Long clientId,
                               @RequestParam(defaultValue = "1") long current,
                               @RequestParam(defaultValue = "10") long size) {
        return orderService.pageTakeoutByClientId(clientId, current, size);
    }

    /**
     * 根据客户ID获取外卖订单数量
     *
     * @param clientId 客户ID
     * @return 外卖订单数量
     */
    @Operation(summary = "根据客户ID获取外卖订单数量", description = "根据客户ID获取外卖订单数量")
    @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true)
    @GetMapping("/count/client/takeout")
    public long countTakeout(@RequestParam Long clientId) {
        return orderService.countTakeoutByClientId(clientId);
    }

    /**
     * 根据客户ID获取快递订单
     *
     * @param clientId 客户ID
     * @param current  当前页
     * @param size     每页大小
     * @return 快递订单
     */
    @Parameters({
            @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "根据客户ID获取快递订单", description = "根据客户ID获取快递订单")
    @GetMapping("/client/express")
    public List<Order> express(@RequestParam Long clientId,
                               @RequestParam(defaultValue = "1") long current,
                               @RequestParam(defaultValue = "10") long size) {
        return orderService.pageExpressByClientId(clientId, current, size);
    }

    /**
     * 根据客户ID获取快递订单数量
     *
     * @param clientId 客户ID
     * @return 快递订单数量
     */
    @Operation(summary = "根据客户ID获取快递订单数量", description = "根据客户ID获取快递订单数量")
    @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true)
    @GetMapping("/count/client/express")
    public long countExpress(@RequestParam Long clientId) {
        return orderService.countExpressByClientId(clientId);
    }

    /**
     * 根据客户ID获取闪送订单
     *
     * @param clientId 客户ID
     * @param current  当前页
     * @param size     每页大小
     * @return 闪送订单
     */
    @Parameters({
            @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "根据客户ID获取闪送订单", description = "根据客户ID获取闪送订单")
    @GetMapping("/client/flash")
    public List<Order> flash(@RequestParam Long clientId,
                             @RequestParam(defaultValue = "1") long current,
                             @RequestParam(defaultValue = "10") long size) {
        return orderService.pageFlashByClientId(clientId, current, size);
    }

    /**
     * 根据客户ID获取闪送订单数量
     *
     * @param clientId 客户ID
     * @return 闪送订单数量
     */
    @Operation(summary = "根据客户ID获取闪送订单数量", description = "根据客户ID获取闪送订单数量")
    @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true)
    @GetMapping("/count/client/flash")
    public long countFlash(@RequestParam Long clientId) {
        return orderService.countFlashByClientId(clientId);
    }

    /**
     * 根据客户ID获取待接单订单
     *
     * @param clientId 客户ID
     * @param current  当前页
     * @param size     每页大小
     * @return 这个客户的待接单订单
     */
    @Parameters({
            @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "根据客户ID获取待接单订单", description = "根据客户ID获取待接单订单")
    @GetMapping("/client/unaccepted")
    public List<Order> unacceptedByClientId(@RequestParam Long clientId,
                                            @RequestParam(defaultValue = "1") long current,
                                            @RequestParam(defaultValue = "10") long size) {
        return orderService.unacceptedByClientId(clientId, current, size);
    }

    /**
     * 获取待接单订单数量
     *
     * @param clientId 客户ID
     * @return 待接单订单数量
     */
    @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "获取待接单订单数量", description = "获取待接单订单数量")
    @GetMapping("/count/client/unaccepted")
    public long countUnacceptedClient(@RequestParam Long clientId) {
        return orderService.count(new LambdaQueryWrapper<Order>()
                .eq(Order::getClientId, clientId)
                .eq(Order::getCurrentStatus, CurrentStatusEnum.PLACED));
    }


    /**
     * 获取所有待接单订单
     *
     * @param current 当前页
     * @param size    每页大小
     * @return 所有待接单订单
     */
    @Parameters({
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "获取所有待接单订单", description = "获取所有待接单订单")
    @GetMapping("/courier/unaccepted")
    public List<Order> unacceptedAll(@RequestParam(defaultValue = "1") long current,
                                     @RequestParam(defaultValue = "10") long size) {
        return orderService.unacceptedAll(current, size);
    }

    /**
     * 根据客户ID获取待收货订单（待确认订单）
     *
     * @param clientId 客户ID
     * @param current  当前页
     * @param size     每页大小
     * @return 待收货订单（待确认订单）
     */
    @Parameters({
            @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "根据客户ID获取待收货订单（待确认订单）", description = "根据客户ID获取待收货订单（待确认订单）")
    @GetMapping("/client/unconfirmed")
    public Object unconfirmed(@RequestParam Long clientId,
                              @RequestParam(defaultValue = "1") long current,
                              @RequestParam(defaultValue = "10") long size) {
        return orderService.pageUnconfirmedByClientId(clientId, current, size);
    }

    /**
     * 获取待收货订单数量
     *
     * @param clientId 客户ID
     * @return 待收货订单数量
     */
    @Parameter(name = "clientId", description = "客户ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "获取待收货订单数量", description = "获取待收货订单数量")
    @GetMapping("/count/client/unconfirmed")
    public long countUnconfirmed(@RequestParam Long clientId) {
        return orderService.countUnconfirmedByClientId(clientId);
    }


    /**
     * 不管状态，根据骑手ID获取所有类别的订单
     *
     * @param courierId 骑手ID
     * @param current   当前页
     * @param size      每页大小
     * @return 不分类、所有类别的订单
     */
    @Parameters({
            @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "不管状态，根据骑手ID获取所有类别的订单", description = "不管状态，根据骑手ID获取所有类别的订单")
    @GetMapping("/courier/all")
    public List<Order> allByCourierId(@RequestParam Long courierId,
                                      @RequestParam(defaultValue = "1") long current,
                                      @RequestParam(defaultValue = "10") long size) {
        return orderService.pageByCourierId(courierId, current, size);
    }

    /**
     * 根据骑手id获取负责的订单数量
     *
     * @param courierId 骑手ID
     * @return 这个骑手负责的订单数量
     */
    @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "根据骑手id获取负责的订单数量", description = "根据骑手id获取负责的订单数量")
    @GetMapping("/count/courier/all")
    public long countByCourierId(@RequestParam Long courierId) {
        return orderService.countByCourierId(courierId);
    }


    /**
     * 根据骑手ID获取待取货订单
     *
     * @param courierId 骑手ID
     * @param current   当前页
     * @param size      每页大小
     * @return 待取货订单
     */
    @Parameters({
            @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "根据骑手ID获取待取货订单", description = "根据骑手ID获取待取货订单")
    @GetMapping("/courier/untransitted")
    public List<Order> untransittedByCourier(@RequestParam Long courierId,
                                             @RequestParam(defaultValue = "1") long current,
                                             @RequestParam(defaultValue = "10") long size) {
        return orderService.pageUntransittedByCourierId(courierId, current, size);
    }


    /**
     * 根据骑手ID获取待取货订单数量
     *
     * @param courierId 骑手ID
     * @return 待取货订单数量
     */
    @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "根据骑手ID获取待取货订单数量", description = "根据骑手ID获取待取货订单数量")
    @GetMapping("/count/courier/untransitted")
    public long countUntransittedByCourier(@RequestParam Long courierId) {
        return orderService.countUntransittedByCourierId(courierId);
    }

    /**
     * 根据骑手ID获取待送达订单
     *
     * @param courierId 骑手ID
     * @param current   当前页
     * @param size      每页大小
     * @return 待送达订单
     */
    @Parameters({
            @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "根据骑手ID获取待送达订单", description = "根据骑手ID获取待送达订单")
    @GetMapping("/courier/transitting")
    public List<Order> transittingByCourier(@RequestParam Long courierId,
                                            @RequestParam(defaultValue = "1") long current,
                                            @RequestParam(defaultValue = "10") long size) {
        return orderService.pageTransittingByCourierId(courierId, current, size);
    }

    /**
     * 根据骑手ID获取配送中订单数量
     *
     * @param courierId 骑手ID
     * @return 配送中订单数量
     */
    @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "根据骑手ID获取配送中订单数量", description = "根据骑手ID获取配送中订单数量")
    @GetMapping("/count/courier/transitting")
    public long countTransittingByCourier(@RequestParam Long courierId) {
        return orderService.countTransittingByCourierId(courierId);
    }

    /**
     * 根据骑手ID获取已送达订单
     *
     * @param courierId 骑手ID
     * @param current   当前页
     * @param size      每页大小
     * @return 已送达订单
     */
    @Parameters({
            @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "根据骑手ID获取已送达订单", description = "根据骑手ID获取已送达订单")
    @GetMapping("/courier/arrived")
    public List<Order> arrivedByCourier(@RequestParam Long courierId,
                                        @RequestParam(defaultValue = "1") long current,
                                        @RequestParam(defaultValue = "10") long size) {
        return orderService.pageArrivedByCourierId(courierId, current, size);
    }

    /**
     * 根据骑手ID获取已送达订单数量
     *
     * @param courierId 骑手ID
     * @return 已送达订单数量
     */
    @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "根据骑手ID获取已送达订单数量", description = "根据骑手ID获取已送达订单数量")
    @GetMapping("/count/courier/arrived")
    public long countArrivedByCourier(@RequestParam Long courierId) {
        return orderService.countArrivedByCourierId(courierId);
    }

    /**
     * 根据骑手ID获取已完成订单
     *
     * @param courierId 骑手ID
     * @param current   当前页
     * @param size      每页大小
     * @return 已完成订单
     */
    @Parameters({
            @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "根据骑手ID获取已完成订单", description = "根据骑手ID获取已完成订单")
    @GetMapping("/courier/confirmed")
    public List<Order> confirmedByCourier(@RequestParam Long courierId,
                                          @RequestParam(defaultValue = "1") long current,
                                          @RequestParam(defaultValue = "10") long size) {
        return orderService.pageConfirmedByCourierId(courierId, current, size);
    }

    /**
     * 根据骑手ID获取已完成订单数量
     *
     * @param courierId 骑手ID
     * @return 已完成订单数量
     */
    @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "根据骑手ID获取已完成订单数量", description = "根据骑手ID获取已完成订单数量")
    @GetMapping("/count/courier/confirmed")
    public long countConfirmedByCourier(@RequestParam Long courierId) {
        return orderService.countConfirmedByCourierId(courierId);
    }

    /**
     * 更新订单的当前状态
     *
     * @param order 订单，必须有 id, currentStatus, courierId
     * @return 如果更新成功，则返回当前时间。否则抛出异常。
     */
    @Operation(summary = "更新订单的当前状态", description = "更新订单的当前状态")
    @Parameter(name = "order", description = "订单", in = ParameterIn.DEFAULT, required = true)
    @PatchMapping("/status")
    public String status(@RequestBody Order order) {
        return date2String(orderService.updateStatus(order.getId(), order.getCurrentStatus(), order.getCourierId()));
    }

    /**
     * 更新订单的评论ID。<br/>
     * 注：创建评论时会同步修改订单的评论ID，一般来说该方法不会被调用，请保证调用时的参数正确性。
     *
     * @param review 评论，必须有id（评论的主键ID）和orderId（对应的订单主键ID）
     * @return 是否更新成功
     */
    @Operation(summary = "更新订单的评论ID。 注：创建评论时会同步修改订单的评论ID，一般来说该方法不会被调用，请保证调用时的参数正确性。", description = "更新订单的评论ID。<br/> 注：创建评论时会同步修改订单的评论ID，一般来说该方法不会被调用，请保证调用时的参数正确性。")
    @PatchMapping("/review")
    public boolean reviewId(@RequestBody Review review) {
        return orderService.updateReviewId(review.getOrderId(), review.getId());
    }

    /**
     * 更新订单的支付ID。<br/>
     * 注：创建支付记录时会同步修改订单的支付ID，一般来说该方法不会被调用，请保证调用时的参数正确性。
     *
     * @param order 订单，必须有id（订单的主键ID）和paymentId（对应的支付记录主键ID）
     * @return 是否更新成功
     */
    @Operation(summary = "更新订单的支付ID。 注：创建支付记录时会同步修改订单的支付ID，一般来说该方法不会被调用，请保证调用时的参数正确性。", description = "更新订单的支付ID。<br/> 注：创建支付记录时会同步修改订单的支付ID，一般来说该方法不会被调用，请保证调用时的参数正确性。")
    @PatchMapping("/payment")
    public boolean paymentId(@RequestBody Order order) {
        return orderService.updatePaymentId(order.getId(), order.getPaymentId());
    }

    /**
     * 新建订单
     * <br>
     * 若没有当前状态，则自动填充为已下单。
     * <br>
     * 新建订单后会自动创建订单跟踪ID。
     *
     * @param order 订单，必须有客户ID，骑手ID，收货地址ID
     * @return 是否插入成功
     */
    @Operation(summary = "新建订单。若没有当前状态，则自动填充为已下单。 新建订单后会自动创建订单跟踪ID。", description = "新建订单 <br> 若没有当前状态，则自动填充为已下单。 <br> 新建订单后会自动创建订单跟踪ID。")
    @Parameter(name = "order", description = "订单", in = ParameterIn.QUERY, required = true)
    @PostMapping("/add")
    public boolean add(@RequestBody Order order) {
        return orderService.save(order);
    }

}