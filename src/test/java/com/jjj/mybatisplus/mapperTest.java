package com.jjj.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jjj.mybatisplus.common.DeptEnum;
import com.jjj.mybatisplus.mapper.DeptMapper;
import com.jjj.mybatisplus.pojo.Dept;
import com.jjj.mybatisplus.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @className: com.jjj.mybatisplus.mapperTest
 * @description:
 * @author: 江骏杰
 * @create: 2022-11-09 21:46
 */
@SpringBootTest
@Slf4j
public class mapperTest {
    @Autowired
    DeptMapper deptMapper;
    @Autowired
    DeptService deptService;

    @Test
    @DisplayName("普通的插入语句")
    public void testInsert01() {
        // INSERT INTO tbl_dept(id, dept_no, dept_name, dept_loc) VALUES (?, ?, ?, ?)
        Dept dept = new Dept();
        dept.setDeptNo(1);
        dept.setDeptLoc("广东");
        dept.setDeptName("研发部");
        int affect = deptMapper.insert(dept);
        log.info("影响行数:{}", affect);
    }

    @Test
    @DisplayName("删除语句-根据Id删除")
    public void testDelete01() {
        // DELETE FROM tbl_dept WHERE id = ?
        int affect = deptMapper.deleteById(1590341586254884865L);
        log.info("影响行数:{}", affect);
    }

    @Test
    @DisplayName("删除语句-根据多个Ids")
    public void testDelete02() {
        // DELETE FROM tbl_dept WHERE id IN ( ? , ? )
        int affect = deptMapper.deleteBatchIds(Arrays.asList(1590342535132258305L, 1590342543327928321L));
        log.info("影响行数:{}", affect);
    }

    @Test
    @DisplayName("删除语句-根据条件删除")
    public void testDelete03() {
        // DELETE FROM tbl_dept WHERE dept_loc = ? 用AND连接
        Map<String, Object> map = new HashMap<>();
        map.put("dept_loc", "上海");
        int affect = deptMapper.deleteByMap(map);
        log.info("影响行数:{}", affect);
    }

    @Test
    @DisplayName("修改语句-根据id进行修改")
    public void testUpdate01() {
        // UPDATE tbl_dept SET dept_loc=? WHERE id=?
        Dept dept = new Dept();
        dept.setId(1590342544338755586L);
        dept.setDeptLoc("上海");
        int affect = deptMapper.updateById(dept);
        log.info("影响行数:{}", affect);
    }


    @Test
    @DisplayName("查询语句-根据id查")
    public void testSelect01() {
        // SELECT id,dept_no,dept_name,dept_loc FROM tbl_dept WHERE id=?
        Dept dept = deptMapper.selectById(1590342549455806466L);
        log.info("实体类对象:{}", dept);
    }

    @Test
    @DisplayName("查询语句-根据多个id查")
    public void testSelect02() {
        // SELECT id,dept_no,dept_name,dept_loc FROM tbl_dept WHERE id IN ( ? , ? )
        List<Dept> depts = deptMapper.selectBatchIds(Arrays.asList(1590342547677421569L, 1590342549455806466L));
        depts.forEach(System.out::println);
    }

    @Test
    @DisplayName("查询语句-根据map条件来查")
    public void testSelect03() {
        Map<String, Object> map = new HashMap<>();
        // 写的是字段名
        map.put("dept_loc", "广东");
        map.put("dept_name", "研发部");
        // SELECT id,dept_no,dept_name,dept_loc FROM tbl_dept WHERE dept_name = ? AND dept_loc = ?
        List<Dept> depts = deptMapper.selectByMap(map);
        depts.forEach(System.out::println);
    }

    @Test
    @DisplayName("查询语句-自定义")
    public void testSelectByOwn() {
        List<Map<String, Object>> maps = deptMapper.selectMaps();
        maps.forEach(System.out::println);
    }

    @Test
    @DisplayName("service测试-批量添加")
    public void testService01() {
        ArrayList<Dept> depts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Dept dept = new Dept();
            dept.setDeptNo(i);
            dept.setDeptLoc("广州:" + i);
            dept.setDeptName("研发部:" + i);
            depts.add(dept);
        }
        // INSERT INTO tbl_dept ( id, dept_no, dept_name, dept_loc ) VALUES ( ?, ?, ?, ? )
        // 只是执行了10次
        deptService.saveBatch(depts);
    }

    @Test
    @DisplayName("service测试-记录数")
    public void testService02() {
        // SELECT COUNT( * ) FROM tbl_dept
        log.info("总记录数:{}", deptService.count());
    }

    @Test
    @DisplayName("逻辑删除")
    public void testLogicDelete01() {
        // UPDATE tbl_dept SET is_deleted=1 WHERE id=? AND is_deleted=0
        int affect = deptMapper.deleteById(1590342544007405569L);
        log.info("影响行数:{}", affect);
    }

    @Test
    @DisplayName("组装查询条件")
    public void testQueryWrapperForSelect() {
        // SELECT id,dept_no,dept_name,dept_loc,is_deleted FROM tbl_dept WHERE is_deleted=0 AND (dept_name = ? AND dept_loc LIKE ?)
        QueryWrapper<Dept> deptQueryWrapper = new QueryWrapper<>();
        deptQueryWrapper.eq("dept_name", "研发部").like("dept_loc", "广");
        List<Dept> depts = deptMapper.selectList(deptQueryWrapper);
        depts.forEach(System.out::println);
    }

    @Test
    @DisplayName("组装排序条件")
    public void testQueryWrapperForOrder() {
        // SELECT id,dept_no,dept_name,dept_loc,is_deleted FROM tbl_dept WHERE is_deleted=0 ORDER BY dept_no ASC,id DESC
        QueryWrapper<Dept> deptQueryWrapper = new QueryWrapper<>();
        deptQueryWrapper.orderByAsc("dept_no").orderByDesc("id");
        List<Dept> depts = deptMapper.selectList(deptQueryWrapper);
        depts.forEach(System.out::println);
    }

    @Test
    @DisplayName("组装删除条件")
    public void testQueryWrapperForDelete() {
        // UPDATE tbl_dept SET is_deleted=1 WHERE is_deleted=0 AND (dept_name LIKE ?)
        QueryWrapper<Dept> deptQueryWrapper = new QueryWrapper<>();
        deptQueryWrapper.like("dept_name", "1");
        int affect = deptMapper.delete(deptQueryWrapper);
        log.info("影响行数:{}", affect);
    }

    @Test
    @DisplayName("组装修改条件1")
    public void testQueryWrapperForUpdate() {
        // UPDATE tbl_dept SET dept_loc=? WHERE is_deleted=0 AND (dept_name = ?)
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_name", "研发部:6");
        Dept dept = new Dept();
        dept.setDeptLoc("杭州");
        int affect = deptMapper.update(dept, queryWrapper);
        log.info("影响行数:{}", affect);
    }

    @Test
    @DisplayName("优先级")
    public void testPriority() {
        // SELECT id,dept_no,dept_name,dept_loc,is_deleted FROM tbl_dept WHERE is_deleted=0 AND (dept_loc = ? AND (dept_no >= ? AND dept_no <= ? OR dept_name LIKE ?))
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_loc", "广东")
                .and(wrapper -> wrapper.ge("dept_no", "0").le("dept_no", "1").or().like("dept_name", "研发"));
        List<Dept> depts = deptMapper.selectList(queryWrapper);
        depts.forEach(System.out::println);
    }

    @Test
    @DisplayName("组转Select语句")
    public void testQueryWrapperForSelectOneOfThem() {
        // SELECT dept_name, dept_no FROM tbl_dept WHERE is_deleted=0
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.select("dept_name, dept_no");
        deptMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    @DisplayName("组装子查询")
    public void testInnerSelect() {
        // SELECT id,dept_no,dept_name,dept_loc,is_deleted FROM tbl_dept WHERE is_deleted=0 AND (dept_no IN (SELECT dept_no FROM tbl_dept WHERE dept_loc = '杭州'))
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("dept_no", "SELECT dept_no FROM tbl_dept WHERE dept_loc = '杭州'");
        deptMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    @Test
    @DisplayName("测试UpdateWrapper修改")
    public void testUpdateWrapper() {
        // UPDATE tbl_dept SET dept_loc=? WHERE is_deleted=0 AND (dept_no BETWEEN ? AND ?)
        UpdateWrapper<Dept> wrapper = new UpdateWrapper<>();
        wrapper.between("dept_no", 1, 3)
                .set("dept_loc", "上海");
        int affect = deptMapper.update(null, wrapper);
        log.info("影响函数{}", affect);
    }

    @Test
    @DisplayName("模拟动态SQL")
    public void imitateCondition() {
        String deptNo = "1";
        String deptName = null;
        String deptLoc = null;
        QueryWrapper<Dept> deptQueryWrapper = new QueryWrapper<>();
        // SELECT id,dept_no,dept_name,dept_loc,is_deleted FROM tbl_dept WHERE is_deleted=0 AND (dept_no = ?)
        if (StringUtils.isNotBlank(deptNo)) {
            deptQueryWrapper.eq("dept_no", deptNo);
        }
        if (StringUtils.isNotBlank(deptName)) {
            deptQueryWrapper.like("dept_name", deptName);
        }
        if (StringUtils.isNotBlank(deptLoc)) {
            deptQueryWrapper.eq("dept_loc", deptLoc);
        }
        deptMapper.selectList(deptQueryWrapper).forEach(System.out::println);
    }

    @Test
    @DisplayName("动态SQL")
    public void condition() {
        // SELECT id,dept_no,dept_name,dept_loc,is_deleted FROM tbl_dept WHERE is_deleted=0 AND (dept_no = ?)
        String deptNo = "1";
        String deptName = null;
        String deptLoc = null;
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(deptNo), "dept_no", deptNo).
                like(StringUtils.isNotBlank(deptName), "dept_name", deptName).
                eq(StringUtils.isNotBlank(deptLoc), "dept_loc", deptLoc);
        deptMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    @Test
    @DisplayName("lambdaQueryWrapper")
    public void lambdaQueryWrapper() {
        LambdaQueryWrapper<Dept> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dept::getDeptLoc, "杭州");
        deptMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    @DisplayName("lambdaUpdateWrapper")
    public void lambdaUpdateWrapper() {
        // UPDATE tbl_dept SET dept_loc=? WHERE is_deleted=0 AND (dept_no = ?)
        LambdaUpdateWrapper<Dept> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Dept::getDeptNo, 1)
                .set(Dept::getDeptLoc, "成都");
        log.info("影响行数{}",deptMapper.update(null, wrapper));
    }

    @Test
    @DisplayName("分页插件的使用")
    public void testPagination() {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Dept::getDeptLoc, "杭");
        Page<Dept> deptPage = new Page<>(2, 3); // 第二页,每一页三条数据
        Page<Dept> page = deptMapper.selectPage(deptPage, queryWrapper);
        log.info("总页数:{}", page.getPages());
        log.info("当前页数:{}", page.getCurrent());
        log.info("每一页的页数:{}", page.getSize());
        log.info("有没有下一页:{}", page.hasNext());
        log.info("有没有上一页:{}", page.hasPrevious());
        log.info("总记录数:{}", page.getTotal());
        log.info("所有的记录:{}", page.getRecords());
    }

    @Test
    @DisplayName("自定义分页")
    public void testPaginationOwn() {
        Page<Dept> deptPage = new Page<>(1, 1);
        Page<Dept> page = deptMapper.selectMyPageById(deptPage, 1590342544007405569L);
        log.info("总记录数:{}", page.getTotal());
    }

    @Test
    @DisplayName("测试乐观锁")
    public void testLock() {
        // 有安全问题才有启动乐观锁,否则不启动
        Dept dept = deptMapper.selectById(1590342544338755586L);
        dept.setDeptNo(dept.getDeptNo()+1);
        deptMapper.updateById(dept);
        dept.setDeptNo(dept.getDeptNo()-1);
        deptMapper.updateById(dept);
    }
    @Test
    @DisplayName("测试枚举")
    public void testEnum() {
        LambdaUpdateWrapper<Dept> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Dept::getDeptLoc, "广州:5")
                .set(Dept::getDeptLoc, DeptEnum.GROUP_TWO);
        log.info("影响行数:{}", deptMapper.update(null, wrapper));
    }



}
