package com.jjj.mybatisplus.mapper;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.jjj.mybatisplus.pojo.Area;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author asus
* @description 针对表【t_area】的数据库操作Mapper
* @createDate 2022-11-11 20:22:31
* @Entity com.jjj.mybatisplus.pojo.Area
*/
public interface AreaMapper extends BaseMapper<Area> {
    List<Area> selectCodeAndNameAndPCodeByIdAndNameLikeOrderByCodeDesc(@Param("id") Integer id, @Param("name") String name);

    int updateNameAndPCodeByCodeBetween(@Param("name") String name, @Param("pCode") Integer pCode, @Param("beginCode") Integer beginCode, @Param("endCode") Integer endCode);

    int insertBatch(@Param("areaCollection") Collection<Area> areaCollection);
}




