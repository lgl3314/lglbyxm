package com.byxm.springbootByxm.modules.account.service;

import com.byxm.springbootByxm.modules.account.entity.*;
import com.byxm.springbootByxm.modules.common.vo.Result;
import com.byxm.springbootByxm.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface StudentsService {

    List<Course> getCourseByCId(int cId);

    List<Students> getStudentById(int sId);

    PageInfo<Students> getStudentsBySearchVo(SearchVo searchVo);

    String login(String information);

    Students getStudentsByAccountNumber(String accountNumber);

    List<Course> getCourseByStudentsId(int studentsId, Course course);

    List<Course> getCourses(Course course);

    Students getStudentBysId(int sId);

    String getStudentBysIdAndPassword(Students students);

    String updateStudentByPassword(Students students);

    List<Course> insertChooseCourse(int sId, List<PickCourse> pickCourses, Course course);
}

