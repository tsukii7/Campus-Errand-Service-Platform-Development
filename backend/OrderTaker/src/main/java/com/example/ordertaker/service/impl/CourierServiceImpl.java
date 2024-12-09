package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordertaker.entity.Blacklist;
import com.example.ordertaker.entity.Courier;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.mapper.CourierMapper;
import com.example.ordertaker.service.BlacklistService;
import com.example.ordertaker.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourierServiceImpl extends ServiceImpl<CourierMapper, Courier> implements CourierService {

    @Autowired
    private BlacklistService blacklistService;

    @Override
    public Courier selectOne(String email, String password) {
        if (email == null || email.isEmpty()
                || password == null || password.isEmpty())
            throw new MyException("骑手信息不完整！");

        return super.baseMapper.selectOne(new LambdaQueryWrapper<Courier>()
                .eq(Courier::getEmail, email)
                .eq(Courier::getPassword, password));
    }

    @Override
    public boolean save(Courier courier) {
        if (courier.getEmail() == null || courier.getEmail().isEmpty()
                || courier.getPassword() == null || courier.getPassword().isEmpty()
                || courier.getCourierName() == null || courier.getCourierName().isEmpty())
            throw new MyException("骑手信息不完整！");

        courier.setRating(0.0);
        courier.setBlockTimes(0);
        return super.save(courier);
    }

    @Override
    public List<Courier> page(long current, long size) {
        return super.page(new Page<>(current, size)).getRecords();
    }

    @Override
    public List<Courier> page(String courierName, long current, long size) {
        if (courierName == null || courierName.isEmpty())
            throw new MyException("骑手信息不完整！");

        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Courier>()
                        .eq(Courier::getCourierName, courierName)
        ).getRecords();
    }

    @Override
    public List<Courier> pageOrderByRatingAsc(long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Courier>().orderByAsc(Courier::getRating)
        ).getRecords();
    }

    @Override
    public List<Courier> pageOrderByRatingDesc(long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Courier>().orderByDesc(Courier::getRating)
        ).getRecords();
    }

    @Override
    public List<Courier> pageOrderByBlockTimesAsc(long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Courier>().orderByAsc(Courier::getBlockTimes)
        ).getRecords();
    }

    @Override
    public List<Courier> pageOrderByBlockTimesDesc(long current, long size) {
        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Courier>().orderByDesc(Courier::getBlockTimes)
        ).getRecords();
    }

    @Override
    public boolean block(Long id) {
        assert (this.setBlockTimes(id, super.getById(id).getBlockTimes() + 1));
        Blacklist blacklist = new Blacklist();
        blacklist.setCourierId(id);
        blacklist.setCreatedTime(new Date());
        return blacklistService.save(blacklist);
    }

    @Override
    public boolean unblock(Long id) {
        if (id == null) {
            throw new MyException("骑手id为空！");
        }

        return blacklistService.remove(
                new LambdaQueryWrapper<Blacklist>()
                        .eq(Blacklist::getCourierId, id)
        );
    }

    @Override
    public boolean setBlockTimes(Long id, Integer blockTimes) {
        if (id == null)
            throw new MyException("骑手id为空！");

        Courier courier = super.getById(id);
        courier.setBlockTimes(blockTimes);
        return super.updateById(courier);
    }

    @Override
    public boolean setRating(Long id, Double rating) {
        if (id == null)
            throw new MyException("骑手id为空！");

        Courier courier = super.getById(id);
        courier.setRating(rating);
        return super.updateById(courier);
    }

    @Override
    public boolean isBlock(Long id) {
        if (id == null)
            throw new MyException("骑手id为空！");

        return blacklistService.getBaseMapper().exists(
                new LambdaQueryWrapper<Blacklist>()
                        .eq(Blacklist::getCourierId, id));
    }
}
