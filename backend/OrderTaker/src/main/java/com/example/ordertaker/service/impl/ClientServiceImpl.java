package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordertaker.entity.Client;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.mapper.ClientMapper;
import com.example.ordertaker.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

    @Override
    public Client selectOne(String email, String password) {
        if (email == null || email.isEmpty()
                || password == null || password.isEmpty())
            throw new MyException("用户信息不完整！");

        return super.baseMapper.selectOne(new LambdaQueryWrapper<Client>()
                .eq(Client::getEmail, email)
                .eq(Client::getPassword, password));
    }

    @Override
    public boolean save(Client client) {
        if (client.getEmail() == null || client.getEmail().isEmpty()
                || client.getPassword() == null || client.getPassword().isEmpty()
                || client.getClientName() == null || client.getClientName().isEmpty())
            throw new MyException("用户信息不完整！");

        return super.save(client);
    }
}
