package com.example.ordertaker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordertaker.entity.Message;

import java.util.List;

public interface MessageService extends IService<Message> {
    @Override boolean save(Message message);

    List<Message> page(Long clientId, Long courierId, long current, long size);

    long count(Long clientId, Long courierId);
}
