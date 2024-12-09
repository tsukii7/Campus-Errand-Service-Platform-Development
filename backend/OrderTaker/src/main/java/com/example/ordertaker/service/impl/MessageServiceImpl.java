package com.example.ordertaker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordertaker.entity.Message;
import com.example.ordertaker.AOP.exceptionInfo.MyException;
import com.example.ordertaker.mapper.MessageMapper;
import com.example.ordertaker.service.MessageService;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Override
    public boolean save(Message message) {
        if (message.getClientId() == null
                || message.getCourierId() == null
                || message.getContent() == null || message.getContent().isEmpty())
            throw new MyException("消息信息不完整！");
        if (!message.getSenderId().equals(message.getClientId()) && !message.getSenderId().equals(message.getCourierId()))
            throw new MyException("发送者ID不是用户ID也不是骑手ID！");

        if (message.getTime() == null)
            message.setTime(new Date());
        return super.save(message);
    }

    @Override
    public List<Message> page(Long clientId, Long courierId, long current, long size) {
        if (clientId == null || courierId == null)
            throw new MyException("查询信息不完整！");

        return super.page(
                new Page<>(current, size),
                new LambdaQueryWrapper<Message>()
                        .eq(Message::getClientId, clientId)
                        .eq(Message::getCourierId, courierId)
                        .orderByDesc(Message::getTime)
        ).getRecords();
    }

    @Override
    public long count(Long clientId, Long courierId) {
        if (clientId == null || courierId == null)
            throw new MyException("查询信息不完整！");

        return super.count(
                new LambdaQueryWrapper<Message>()
                        .eq(Message::getClientId, clientId)
                        .eq(Message::getCourierId, courierId)
        );
    }
}
