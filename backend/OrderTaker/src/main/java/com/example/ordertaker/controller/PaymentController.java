package com.example.ordertaker.controller;

import com.example.ordertaker.constant.PaymentStatusEnum;
import com.example.ordertaker.entity.Payment;
import com.example.ordertaker.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Tag(name = "支付接口", description = "/api/payment")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    /**
     * 创建支付记录。
     * <br>
     * 若没有当前状态，则自动填充为待支付。
     * <br>
     * 若没有创建时间，则自动填充为当前时间。
     * <br>
     * 创建支付记录后会自动更新订单状态。
     *
     * @param payment 支付记录。必须有交易ID，订单ID
     * @return 是否创建成功
     */
    @Operation(summary = "创建支付记录。若没有当前状态，则自动填充为待支付。若没有创建时间，则自动填充为当前时间。创建支付记录后会自动更新订单状态。", description = "创建支付记录。 <br> 若没有当前状态，则自动填充为待支付。 <br> 若没有创建时间，则自动填充为当前时间。 <br> 创建支付记录后会自动更新订单状态。")
    @Parameter(name = "payment", description = "支付记录。必须有交易ID，订单ID", in = ParameterIn.QUERY, required = true)
    @PostMapping("/add")
    public boolean add(@RequestBody Payment payment) {
        return paymentService.save(payment);
    }

    /**
     * 更新支付记录
     *
     * @param payment 支付记录，必须有ID和状态
     * @return 是否更新成功
     */
    @Operation(summary = "更新支付记录", description = "更新支付记录")
    @Parameter(name = "payment", description = "支付记录，必须有ID和状态", in = ParameterIn.QUERY, required = true)
    @PatchMapping("/update")
    public boolean update(@RequestBody Payment payment) {
        return paymentService.updateStatus(payment.getId(), payment.getStatus());
    }

    /**
     * 获取支付状态
     *
     * @param id 支付记录ID
     * @return 支付状态
     */
    @Parameter(name = "id", description = "支付记录ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "获取支付状态", description = "获取支付状态")
    @GetMapping("/status")
    public PaymentStatusEnum getStatus(@RequestParam Long id) {
        return paymentService.getStatus(id);
    }
}
