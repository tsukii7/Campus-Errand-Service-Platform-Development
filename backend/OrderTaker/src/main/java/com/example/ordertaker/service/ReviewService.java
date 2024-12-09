package com.example.ordertaker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordertaker.entity.Review;
import io.swagger.models.auth.In;

import java.util.List;

public interface ReviewService extends IService<Review> {
    @Override
    boolean save(Review review);

    boolean removeById(Long id);

    boolean updateReview(Long id, String comment, Double rating);
    
    List<Review> page(long current, long size);
    
    List<Review> pageByClient(Long clientId, long current, long size);
    
    List<Review> pageByCourier(Long courierId, long current, long size);

    long countByClientId(Long clientId);

    long countByCourierId(Long courierId);
}
