package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.entity.Review;
import com.example.ordertaker.mapper.ReviewMapper;
import com.example.ordertaker.service.OrderService;
import com.example.ordertaker.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

    @Autowired
    private OrderService orderService;

    @Override
    public boolean save(Review review) {
        if (review.getOrderId() == null
                || review.getClientId() == null
                || review.getCourierId() == null)
            throw new MyException("评论信息不完整！");
        if ((review.getComment() == null || review.getComment().isEmpty()) && review.getRating() == null)
            throw new MyException("评论信息不完整！");

        if (review.getCreatedTime() == null)
            review.setCreatedTime(new Date());
        if (super.save(review)) {
            orderService.updateReviewId(review.getOrderId(), review.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeById(Long id) {
        if (id == null)
            throw new RuntimeException("主键ID为空！");

        Review review = super.getById(id);
        if (review == null)
            throw new MyException("评论不存在！");

        if (orderService.removeReviewId(review.getOrderId()))
            return super.removeById(id);
        else
            return false;
    }

    @Override
    public boolean updateReview(Long id, String comment, Double rating) {
        if (id == null)
            throw new MyException("评论信息不完整！");
        if ((comment == null || comment.isEmpty()) && rating == null)
            throw new MyException("评论信息不完整！");


        LambdaUpdateWrapper<Review> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Review::getId, id);
        if (comment != null && !comment.isEmpty())
            wrapper.set(Review::getComment, comment);
        if (rating != null)
            wrapper.set(Review::getRating, rating);
        return super.update(wrapper);
    }

    @Override
    public List<Review> page(long current, long size) {
        return super.page(new Page<>(current, size)).getRecords();
    }

    @Override
    public List<Review> pageByClient(Long clientId, long current, long size) {
        if (clientId == null)
            throw new MyException("客户ID为空！");

        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Review>()
                        .eq(Review::getClientId, clientId)
        ).getRecords();
    }

    @Override
    public List<Review> pageByCourier(Long courierId, long current, long size) {
        if (courierId == null)
            throw new MyException("骑手ID为空！");

        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Review>()
                        .eq(Review::getCourierId, courierId)
        ).getRecords();
    }

    @Override
    public long countByClientId(Long clientId) {
        if (clientId == null)
            throw new MyException("客户ID为空！");

        return super.count(
                new LambdaQueryWrapper<Review>()
                        .eq(Review::getClientId, clientId)
        );
    }

    @Override
    public long countByCourierId(Long courierId) {
        if (courierId == null)
            throw new MyException("骑手ID为空！");

        return super.count(
                new LambdaQueryWrapper<Review>()
                        .eq(Review::getCourierId, courierId)
        );
    }
}
