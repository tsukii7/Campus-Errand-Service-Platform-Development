package com.example.ordertaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ordertaker.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Update("update `order` set current_status = 3 where courier_id is not null and now() > expected_arrived_time and (current_status = 1 or current_status = 2)")
    void checkStatus();
}
