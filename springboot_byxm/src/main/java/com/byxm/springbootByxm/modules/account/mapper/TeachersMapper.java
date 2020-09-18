package com.byxm.springbootByxm.modules.account.mapper;

import com.byxm.springbootByxm.modules.account.entity.Teachers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeachersMapper {

    @Select("select * from teachers where t_id=#{tId}")
    List<Teachers>  getTeachersByTId(int tId);

    @Select("select * from teachers where t_number = #{name}")
    Teachers getTeachersBytName(String name);
}
