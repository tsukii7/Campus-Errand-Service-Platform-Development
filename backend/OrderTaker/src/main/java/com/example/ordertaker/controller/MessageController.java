package com.example.ordertaker.controller;

import com.example.ordertaker.entity.Message;
import com.example.ordertaker.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "聊天记录接口", description ="/api/message" )
@RestController
@RequestMapping("/api/message")
public class MessageController {
    
    @Autowired
    private MessageService messageService;

    /**
     * 添加用户与骑手的聊天记录
     *
     * @param message 聊天记录
     * @return 是否添加成功
     */
    @Operation(summary = "添加用户与骑手的聊天记录", description = "添加用户与骑手的聊天记录")
    @Parameter(name = "message", description = "聊天记录", in = ParameterIn.QUERY, required = true)
    @PostMapping("")
    public boolean add(@RequestBody Message message) {
        return messageService.save(message);
    }


    /**
     * 获取用户和骑手的聊天记录，按时间降序排列
     *
     * @param clientId  用户ID
     * @param courierId 骑手ID
     * @param current   当前页
     * @param size      每页大小
     * @return 聊天记录
     */
    @Parameters({
            @Parameter(name = "clientId", description = "用户ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "size", description = "每页大小", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "获取用户和骑手的聊天记录，按时间降序排列", description = "获取用户和骑手的聊天记录，按时间降序排列")
    @GetMapping("")
    public List<Message> page(@RequestParam Long clientId,
                              @RequestParam Long courierId,
                              @RequestParam(defaultValue = "1") long current,
                              @RequestParam(defaultValue = "10") long size) {
        return messageService.page(clientId, courierId, current, size);
    }


    /**
     * 获取用户和骑手的聊天记录数量
     *
     * @param clientId  用户ID
     * @param courierId 骑手ID
     * @return 聊天记录数量
     */
    @Operation(summary = "获取用户和骑手的聊天记录数量", description = "获取用户和骑手的聊天记录数量")
    @Parameters({
            @Parameter(name = "clientId", description = "用户ID", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true)
    })
    @GetMapping("/count")
    public long count(@RequestParam Long clientId,
                      @RequestParam Long courierId) {
        return messageService.count(clientId, courierId);
    }

}
