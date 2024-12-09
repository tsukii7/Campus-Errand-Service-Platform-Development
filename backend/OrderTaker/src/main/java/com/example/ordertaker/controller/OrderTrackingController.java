package com.example.ordertaker.controller;

import com.example.ordertaker.OrderTakerApplication;
import com.example.ordertaker.entity.OrderTracking;
import com.example.ordertaker.service.OrderTrackingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Tag(name ="订单跟踪接口" , description ="/api/track" )
@RestController
@RequestMapping("/api/track")
public class OrderTrackingController {
    @Autowired
    private OrderTrackingService orderTrackingService;

    /**
     * 创建订单跟踪记录。<br>
     * 若无创建时间，则默认为当前时间。<br>
     * 创建后会自动更新订单的跟踪ID。
     *
     * @param orderTracking 订单跟踪记录，必须有订单ID。
     * @return 是否新增成功
     */
    @Operation(summary = "创建订单跟踪记录。 若无创建时间，则默认为当前时间。 创建后会自动更新订单的跟踪ID。", description = "创建订单跟踪记录。 若无创建时间，则默认为当前时间。 创建后会自动更新订单的跟踪ID。")
    @Parameter(name = "orderTracking", description = "订单跟踪记录，必须有订单ID。", in = ParameterIn.QUERY, required = true)
    @PostMapping("")
    public boolean add(@RequestBody OrderTracking orderTracking) {
        return orderTrackingService.save(orderTracking);
    }

    /**
     * 获取订单跟踪记录。
     *
     * @param id 订单跟踪记录ID
     * @return 订单跟踪记录
     */
    @Parameter(name = "id", description = "订单跟踪记录ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "获取订单跟踪记录。", description = "获取订单跟踪记录。")
    @GetMapping("")
    public OrderTracking getById(@RequestParam Long id) {
        return orderTrackingService.getById(id);
    }

    /**
     * 更新下单时间。
     *
     * @param orderTracking 订单跟踪记录，必须有id和placedTime
     * @return 是否更新成功
     */
    @Operation(summary = "更新下单时间。", description = "更新下单时间。")
    @Parameter(name = "orderTracking", description = "订单跟踪记录，必须有id和placedTime", in = ParameterIn.QUERY, required = true)
    @PostMapping("/place")
    public boolean place(@RequestBody OrderTracking orderTracking) {
        return orderTrackingService.updatePlacedTime(orderTracking.getId(), orderTracking.getPlacedTime());
    }

    /**
     * 更新开始配送时间。
     *
     * @param orderTracking 订单跟踪记录，必须有id和transitedTime
     * @return 是否更新成功
     */
    @Operation(summary = "更新开始配送时间。", description = "更新开始配送时间。")
    @Parameter(name = "orderTracking", description = "订单跟踪记录，必须有id和transitedTime", in = ParameterIn.QUERY, required = true)
    @PostMapping("/transit")
    public boolean transit(@RequestBody OrderTracking orderTracking) {
        return orderTrackingService.updateTransitedTime(orderTracking.getId(),orderTracking.getTransitedTime());
    }

    /**
     * 更新送达时间。
     *
     * @param orderTracking 订单跟踪记录，必须有id和arrivedTime
     * @return 是否更新成功
     */
    @Operation(summary = "更新送达时间。", description = "更新送达时间。")
    @Parameter(name = "orderTracking", description = "订单跟踪记录，必须有id和arrivedTime", in = ParameterIn.QUERY, required = true)
    @PostMapping("/arrive")
    public boolean arrive(@RequestBody OrderTracking orderTracking) {
        return orderTrackingService.updateArrivedTime(orderTracking.getId(), orderTracking.getArrivedTime());
    }

    /**
     * 更新确认时间。
     *
     * @param orderTracking 订单跟踪记录，必须有id和confirmedTime
     * @return 是否更新成功
     */
    @Operation(summary = "更新确认时间。", description = "更新确认时间。")
    @Parameter(name = "orderTracking", description = "订单跟踪记录，必须有id和confirmedTime", in = ParameterIn.QUERY, required = true)
    @PostMapping("/confirm")
    public boolean confirm(@RequestBody OrderTracking orderTracking) {
        return orderTrackingService.updateConfirmedTime(orderTracking.getId(), orderTracking.getConfirmedTime());
    }

    /**
     * 更新取消时间。
     *
     * @param orderTracking 订单跟踪记录，必须有id和cancelledTime
     * @return 是否更新成功
     */
    @Operation(summary = "更新取消时间。", description = "更新取消时间。")
    @Parameter(name = "orderTracking", description = "订单跟踪记录，必须有id和cancelledTime", in = ParameterIn.QUERY, required = true)
    @PostMapping("/cancel")
    public boolean cancel(@RequestBody OrderTracking orderTracking) {
        return orderTrackingService.updateCancelledTime(orderTracking.getId(), orderTracking.getCancelledTime());
    }
}