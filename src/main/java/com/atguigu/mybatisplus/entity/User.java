package com.atguigu.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {

//    @TableId(type = IdType.AUTO) // 主键自增
    @TableId(type = IdType.ID_WORKER) // 分布式id生成器，默认
//    @TableId(type = IdType.ID_WORKER_STR) // String类型的分布式id生成器
//    @TableId(type = IdType.UUID) // 随机字符串
//    @TableId(type = IdType.INPUT) // 自己输入id
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;
}
