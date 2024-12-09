package com.example.ordertaker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordertaker.entity.Blacklist;

import java.util.List;


public interface BlacklistService extends IService<Blacklist> {

    List<Blacklist> page(long current, long size);

    List<Blacklist> page(String courierName, long current, long size);

    List<Blacklist> pageOrderByCreatedTimeAsc(long current, long size);

    List<Blacklist> pageOrderByCreatedTimeDesc(long current, long size);
}
