package com.example.ordertaker.controller;

import com.example.ordertaker.entity.Review;
import com.example.ordertaker.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "评价接口", description = "/api/review")
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * 创建一条评价。<br>
     * 若无创建时间，则默认为当前时间。<br>
     * 创建后会自动更新订单的评价ID。
     *
     * @param review 评价，必须有{@code clientId}，{@code courierId}，{@code orderId}。且 {@code rating} 和 {@code comment} 不能同时为空
     * @return 是否新增成功
     */
    @Operation(summary = "创建一条评价", description = "创建一条评价")
    @Parameter(name = "review", description = "评价，必须有 clientId，courierId，orderId。且 rating 和 comment 不能同时为空", in = ParameterIn.QUERY, required = true)
    @PostMapping("/add")
    public boolean add(@RequestBody Review review) {
        return reviewService.save(review);
    }


    /**
     * 查询用户提交的所有评价
     *
     * @param clientId 用户的ID
     * @param current  当前页
     * @param size     每页大小
     * @return 用户提交的所有评价
     */
    @Parameters({
            @Parameter(name = "clientId", description = "用户的ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "查询用户提交的所有评价", description = "查询用户提交的所有评价")
    @GetMapping("/client")
    public List<Review> getByClient(@RequestParam Long clientId,
                                    @RequestParam(defaultValue = "1") long current,
                                    @RequestParam(defaultValue = "10") long size) {
        return reviewService.pageByClient(clientId, current, size);
    }

    /**
     * 查询用户提交的所有评价的数量
     *
     * @param clientId 用户的ID
     * @return 用户提交的所有评价的数量
     */
    @Parameter(name = "clientId", description = "用户的ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "查询用户提交的所有评价的数量", description = "查询用户提交的所有评价的数量")
    @GetMapping("count/client")
    public long countByClientId(@RequestParam Long clientId) {
        return reviewService.countByClientId(clientId);
    }


    /**
     * 查询骑手获得的所有评价
     *
     * @param courierId 骑手的ID
     * @param current   当前页
     * @param size      每页大小
     * @return 骑手获得的所有评价
     */
    @Parameters({
            @Parameter(name = "courierId", description = "骑手的ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "查询骑手获得的所有评价", description = "查询骑手获得的所有评价")
    @GetMapping("/courier")
    public List<Review> getByCourier(@RequestParam Long courierId,
                                     @RequestParam(defaultValue = "1") long current,
                                     @RequestParam(defaultValue = "10") long size) {
        return reviewService.pageByCourier(courierId, current, size);
    }

    /**
     * 查询骑手获得的所有评价的数量
     *
     * @param courierId 骑手的ID
     * @return 骑手获得的所有评价的数量
     */
    @Parameter(name = "courierId", description = "骑手的ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "查询骑手获得的所有评价的数量", description = "查询骑手获得的所有评价的数量")
    @GetMapping("count/courier")
    public long countByCourierId(@RequestParam Long courierId) {
        return reviewService.countByCourierId(courierId);
    }

    /**
     * 删除一条评价
     * <br>
     * 注：删除后会自动更新订单的评价ID为 {@code null} 。
     *
     * @param id 评价的主键ID
     * @return 是否删除成功
     */
    @Parameter(name = "id", description = "评价的主键ID", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "删除一条评价  注：删除后会自动更新订单的评价ID为 {@code null} 。", description = "删除一条评价 注：删除后会自动更新订单的评价ID为 {@code null} 。")
    @DeleteMapping("/delete")
    public boolean delete(@RequestParam Long id) {
        return reviewService.removeById(id);
    }

    /**
     * 更新一条评价
     *
     * @param review 评价，必须有 {@code id}，且 {@code comment} 和 {@code rating} 不能为空 。
     * @return 是否更新成功
     */
    @Operation(summary = "更新一条评价", description = "更新一条评价")
    @Parameter(name = "review", description = "评价，必须有 id，且 comment 和 rating 不能为空 。", in = ParameterIn.QUERY, required = true)
    @PatchMapping("/update")
    public boolean update(@RequestBody Review review) {
        return reviewService.updateReview(review.getId(), review.getComment(), review.getRating());
    }
}
