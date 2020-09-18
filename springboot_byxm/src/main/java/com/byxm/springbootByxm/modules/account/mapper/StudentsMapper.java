package com.byxm.springbootByxm.modules.account.mapper;


import com.byxm.springbootByxm.modules.account.entity.*;
import com.byxm.springbootByxm.modules.account.entity.System;
import com.byxm.springbootByxm.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentsMapper {

    @Select("<script>" +
            "SELECT students.s_id,students.account_number,course.c_name,course.credits,achievements.scores,achievements.score_points FROM course\n" +
            "INNER JOIN achievements ON achievements.c_id = course.c_id\n" +
            "INNER JOIN students ON students.s_id = achievements.s_id\n "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (students.account_number like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by students.s_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Students> getStudentsBySearchVo(SearchVo searchVo);

    @Select("select * from students where p_id=#{pId}")
    List<Students> getStudentByPId(int pId);

    @Select("SELECT students.s_id,students.account_number,students.s_name FROM students WHERE s_id=#{sId}")
    List<Students> getStudentById(int sId);

    @Select("SELECT\n" +
            "text_books.text_book\n" +
            "FROM\n" +
            "text_books\n where c_id = #{cId}")
    List<TextBooks> gettextBookBytextBookCId(int cId);

/*===============================================*/

    @Select("select * from students where account_number = #{accountNumber}")
    Students getStudentsByAccountNumber(String accountNumber);

    @Select("SELECT course.* FROM students INNER JOIN pick_course " +
            "ON students.s_id = pick_course.s_id INNER JOIN course " +
            "ON pick_course.c_id = course.c_id " +
            "where students.s_id = #{sId} and c_time = #{cTime} and semester = #{semester}")
    List<Course> getCourseByStudentsId(int sId, String cTime, String semester);

    @Select("select * from course where c_time = #{cTime} and semester = #{semester}")
    List<Course> getCourses(String cTime, String semester);

    @Select("select * from students where s_id = #{sId}")
    Students getStudentBysId(int sId);

    @Update("update students set password = #{password} where account_number = #{accountNumber}")
    int updateStudentByPassword(Students students);

    @Select("select * from students where account_number = #{accountNumber} and password = #{password}")
    Students getStudentBysIdAndPassword(Students students);

    @Select("select * from professional where p_id = #{pId}")
    Professional getProfessionalBypId(int pId);

    @Select("select * from system where sys_id = #{sysId}")
    System getSystemBySysId(int sysId);

    @Select("select * from hospital where h_id = #{hId}")
    Hospital getHospitalByhId(int hId);

    @Select("select * from teachers where t_id = #{tId}")
    List<Teachers> getTeacherBytId(int tId);

    @Insert("insert into pick_course (s_id, c_id) values (#{sId}, #{cId})")
    void insertChooseCourse(int studentId, int cId);

}

