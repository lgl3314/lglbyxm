package com.byxm.springbootByxm.modules.account.mapper;

import com.byxm.springbootByxm.modules.account.entity.Course;
import com.byxm.springbootByxm.modules.account.entity.TextBooks;
import com.byxm.springbootByxm.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseMapper {
    @Select("select * from course where c_id = #{cId}")
    List<Course> getCourseByCId(int cId);

    @Select("<script>" +
            "SELECT text_books.text_book, course.semester,professional.sys_id,course.c_id,course.c_name,professional.p_id,text_books.te_id FROM text_books\n" +
            "INNER JOIN course ON course.c_id = text_books.c_id\n" +
            "INNER JOIN professional ON professional.sys_id = course.sys_id\n"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (professional.p_id like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by professional.sys_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<TextBooks> getTextBookBySearchVo(SearchVo searchVo);


    @Select("<script>" +
            "SELECT professional.p_name,professional.sys_id,course.c_time,course.c_place,course.c_name,course.c_id,course.num,course.t_id,teachers.t_name FROM course\n" +
            "INNER JOIN professional ON professional.sys_id = course.sys_id\n" +
            "INNER JOIN teachers ON course.t_id = teachers.t_id\n"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (professional.p_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by professional.sys_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Course> getCourseBySearchVo(SearchVo searchVo);
}
