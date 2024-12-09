package com.example.ordertaker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordertaker.entity.Client;

public interface ClientService extends IService<Client> {

    Client selectOne(String email, String password);

    @Override
    boolean save(Client client);
}
