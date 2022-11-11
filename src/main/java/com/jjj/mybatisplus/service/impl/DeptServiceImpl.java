package com.jjj.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jjj.mybatisplus.mapper.DeptMapper;
import com.jjj.mybatisplus.pojo.Dept;
import com.jjj.mybatisplus.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * @className: com.jjj.mybatisplus.service.impl.DeptServiceImpl
 * @description:
 * @author: 江骏杰
 * @create: 2022-11-10 17:12
 */
@Service
@DS("master")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
}
