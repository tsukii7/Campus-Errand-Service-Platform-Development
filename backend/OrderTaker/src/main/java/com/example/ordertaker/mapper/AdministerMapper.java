package com.example.ordertaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ordertaker.entity.Administer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

@Mapper
public interface AdministerMapper extends BaseMapper<Administer> {

}
