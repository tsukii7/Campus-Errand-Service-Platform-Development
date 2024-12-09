package com.example.ordertaker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("administer")
public class Administer {

    @Schema(description = "管理员主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "管理员名字")
    @TableField()
    private String adminName;

    @Schema( description = "管理员密码")
    @TableField()
    private String password;

}
