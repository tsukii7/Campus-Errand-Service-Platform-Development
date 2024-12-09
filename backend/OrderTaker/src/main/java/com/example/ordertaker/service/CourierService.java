package com.example.ordertaker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordertaker.entity.Courier;

import java.util.List;

public interface CourierService extends IService<Courier> {

    Courier selectOne(String email, String password);

    @Override
    boolean save(Courier courier);

    List<Courier> page(long current, long size);

    List<Courier> page(String courierName, long current, long size);

    List<Courier> pageOrderByRatingAsc(long current, long size);

    List<Courier> pageOrderByRatingDesc(long current, long size);

    List<Courier> pageOrderByBlockTimesAsc(long current, long size);

    List<Courier> pageOrderByBlockTimesDesc(long current, long size);

    boolean block(Long id);

    boolean unblock(Long id);

    boolean setBlockTimes(Long id, Integer blockTimes);

    boolean setRating(Long id, Double rating);

    boolean isBlock(Long id);

}
