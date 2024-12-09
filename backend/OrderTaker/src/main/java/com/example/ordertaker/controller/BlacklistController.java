package com.example.ordertaker.controller;

import com.example.ordertaker.service.BlacklistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="黑名单接口" , description ="/api/blacklist" )
@RestController
@RequestMapping("/api/blacklist")
public class BlacklistController {
    @Autowired
    private BlacklistService blacklistService;

}