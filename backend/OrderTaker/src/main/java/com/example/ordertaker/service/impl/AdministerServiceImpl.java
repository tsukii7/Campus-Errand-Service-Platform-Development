package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordertaker.entity.Administer;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.mapper.AdministerMapper;
import com.example.ordertaker.service.AdministerService;
import org.springframework.stereotype.Service;

@Service
public class AdministerServiceImpl extends ServiceImpl<AdministerMapper, Administer> implements AdministerService {

    @Override
    public Administer checkAdministerExist(String adminName, String password) {
        if (adminName == null || adminName.isEmpty()
                || password == null || password.isEmpty())
            throw new MyException("管理员信息不完整！");

        return super.baseMapper.selectOne(new LambdaQueryWrapper<Administer>()
                .eq(Administer::getAdminName, adminName)
                .eq(Administer::getPassword, password));
    }
}
