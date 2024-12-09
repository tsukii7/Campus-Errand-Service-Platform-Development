package com.example.ordertaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ordertaker.entity.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper extends BaseMapper<Review> {
}
