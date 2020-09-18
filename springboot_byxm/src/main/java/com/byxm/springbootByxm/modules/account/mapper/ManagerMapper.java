package com.byxm.springbootByxm.modules.account.mapper;

import com.byxm.springbootByxm.modules.account.entity.Managers;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface ManagerMapper {

    @Select("select * from managers where m_number = #{name}")
    Managers getManagerBymName(String name);
}
