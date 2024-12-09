package com.example.ordertaker.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ordertaker.entity.Courier;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourierMapper extends BaseMapper<Courier> {

}
