package com.example.ordertaker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordertaker.entity.Administer;


public interface AdministerService extends IService<Administer> {
    Administer checkAdministerExist(String adminName, String password);
}
