package com.jjj.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jjj.mybatisplus.pojo.Dept;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @className: com.jjj.mybatisplus.mapper.HelloMapper
 * @description:
 * @author: 江骏杰
 * @create: 2022-11-09 21:44
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    List<Map<String, Object>> selectMaps();

    Page<Dept> selectMyPageById(Page<Dept> page, Long id);
}
