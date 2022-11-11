package com.jjj.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_area
 */
@TableName(value ="t_area")
@Data
public class Area implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer code;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer pCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}