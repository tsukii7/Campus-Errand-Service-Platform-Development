package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordertaker.entity.Blacklist;
import com.example.ordertaker.entity.Courier;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.mapper.BlacklistMapper;
import com.example.ordertaker.service.BlacklistService;
import com.example.ordertaker.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements BlacklistService {

    @Autowired
    @Lazy
    private CourierService courierService;

    @Override
    public List<Blacklist> page(long current, long size) {
        return super.page(new Page<>(current, size)).getRecords();
    }

    @Override
    public List<Blacklist> page(String courierName, long current, long size) {
        if (courierName == null || courierName.isEmpty())
            throw new MyException("骑手姓名为空！");

        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Blacklist>()
                        .in(Blacklist::getCourierId, courierService
                                .list(new LambdaQueryWrapper<Courier>()
                                        .select(Courier::getId)
                                        .eq(Courier::getCourierName, courierName)
                                ))
        ).getRecords();
    }

    @Override
    public List<Blacklist> pageOrderByCreatedTimeAsc(long current, long size) {
        return super.page(new Page<>(current, size),
                new LambdaQueryWrapper<Blacklist>()
                        .orderByAsc(Blacklist::getCreatedTime)
        ).getRecords();
    }

    @Override
    public List<Blacklist> pageOrderByCreatedTimeDesc(long current, long size) {
        return super.page(new Page<>(current, size),
                new LambdaQueryWrapper<Blacklist>()
                        .orderByDesc(Blacklist::getCreatedTime)
        ).getRecords();
    }
}
