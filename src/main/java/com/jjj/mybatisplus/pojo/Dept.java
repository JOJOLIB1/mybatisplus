package com.jjj.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @className: com.jjj.pojo.Dept
 * @description:
 * @author: 江骏杰
 * @create: 2022-11-09 21:32
 */
@Data
@TableName("tbl_dept") // 指定映射对应的表
public class Dept {
    @TableId(value = "id", type = IdType.ASSIGN_ID) // 指定主键,不是属性id就不认识, 并制定主键生成策略(默认的雪花算法)
    private Long id;
    @TableField("dept_no") // 驼峰和下划线是可以默认映射的
    private Integer deptNo;
    private String deptName;
    private String deptLoc;
    @TableLogic
    private byte isDeleted;
    @Version
    private Integer version;
}
