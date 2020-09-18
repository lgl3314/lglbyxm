package com.byxm.springbootByxm.modules.account.mapper;


import com.byxm.springbootByxm.modules.account.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMapper {

    @Select("SELECT r_name FROM role r INNER JOIN students s " +
            "ON r.r_id = s.r_id where s.s_id = #{sId}")
    Role getRoleByStudentsId(int sId);
}
