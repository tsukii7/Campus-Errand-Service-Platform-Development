package com.example.ordertaker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ordertaker.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClientMapper extends BaseMapper<Client> {

}
