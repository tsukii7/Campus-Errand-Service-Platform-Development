package com.example.ordertaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ordertaker.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
