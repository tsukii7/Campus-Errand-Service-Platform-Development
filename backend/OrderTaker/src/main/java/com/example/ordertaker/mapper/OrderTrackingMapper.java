package com.example.ordertaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ordertaker.entity.OrderTracking;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderTrackingMapper extends BaseMapper<OrderTracking> {
}
