package com.byxm.springbootByxm.modules.account.service.impl;

import com.byxm.springbootByxm.modules.account.entity.*;
import com.byxm.springbootByxm.modules.account.entity.System;
import com.byxm.springbootByxm.modules.account.mapper.*;
import com.byxm.springbootByxm.modules.account.service.StudentsService;
import com.byxm.springbootByxm.modules.common.vo.Result;
import com.byxm.springbootByxm.modules.common.vo.SearchVo;
import com.byxm.springbootByxm.utils.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsMapper studentsMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeachersMapper teachersMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public String login(String information) {
        Subject subject = SecurityUtils.getSubject();
//        将字符串分割
        String[] ss = information.split(" ");
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(ss[0], MD5Util.getMD5(ss[1]));
        try {
//            进入realm进行认证
            subject.login(usernamePasswordToken);
//                subject.checkRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return "用户名或密码错误";
        }
        return "登陆成功";

    }


    @Override
    public List<Course> getCourseByCId(int cId) {
        List<Course> course = courseMapper.getCourseByCId(cId);
        for (Course teachers : course){
            teachers.setTeachers(teachersMapper.getTeachersByTId(teachers.gettId()));
        }
        return course;
    }

    @Override
    public List<Students> getStudentById(int sId){
        List<Students> student1 = studentsMapper.getStudentById(sId);
        for (Students payment : student1){
            payment.setPayments(paymentMapper.getPaymentBySId(payment.getsId()));
        }
        return student1;
    }

    @Override
    public PageInfo<Students> getStudentsBySearchVo(SearchVo searchVo) {
       searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<Students>(Optional
                .ofNullable(studentsMapper.getStudentsBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    /*=======================================================================*/
    @Override
    public Students getStudentsByAccountNumber(String name) {
        return studentsMapper.getStudentsByAccountNumber(name);
    }

    @Override
    public List<Course> getCourseByStudentsId(int studentsId, Course course) {
        List<Course> courses = studentsMapper.getCourseByStudentsId(studentsId, course.getcTime(), course.getSemester());
        for (Course cours : courses) {
            List<Teachers> teachers = studentsMapper.getTeacherBytId(cours.gettId());
            com.byxm.springbootByxm.modules.account.entity.System system = studentsMapper.getSystemBySysId(cours.getSysId());
            cours.setTeachers( teachers);
            cours.setSystem(system);
        }
        return courses;
    }

    @Override
    public List<Course> getCourses(Course course) {
        List<Course> courses = studentsMapper.getCourses(course.getcTime(), course.getSemester());
        for (Course cours : courses) {
            List<Teachers> teachers = studentsMapper.getTeacherBytId(cours.gettId());
            com.byxm.springbootByxm.modules.account.entity.System system = studentsMapper.getSystemBySysId(cours.getSysId());
            cours.setTeachers(teachers);
            cours.setSystem(system);
        }
        return courses;
    }

    @Override
    public Students getStudentBysId(int sId) {
        Students students = studentsMapper.getStudentBysId(sId);
        Professional professional = studentsMapper.getProfessionalBypId(students.getpId());
        System system = studentsMapper.getSystemBySysId(professional.getpId());
        Hospital hospital = studentsMapper.getHospitalByhId(system.gethId());
        students.setProfessional(professional);
        students.setSystem(system);
        students.setHospital(hospital);
        return students;
    }

    @Override
    public String getStudentBysIdAndPassword(Students students) {
//        将传进来的密码加密
        String pwd = MD5Util.getMD5(students.getPassword());
        students.setPassword(pwd);
//        将加密后的密码与数据库作比较
        Students students1 = studentsMapper.getStudentBysIdAndPassword(students);
        if (students1 == null){
            return "错误";
        }
        return "正确";
    }

    @Override
    public String updateStudentByPassword(Students students) {
        //        将传进来的密码加密
        String pwd = MD5Util.getMD5(students.getPassword());
        students.setPassword(pwd);
        int i = studentsMapper.updateStudentByPassword(students);
        if (i == 0){
            return "修改失败";
        }
        return "修改成功";
    }

    @Override
    public List<Course> insertChooseCourse(int sId, List<PickCourse> pickCourses, Course course) {
        if (pickCourses != null) {
            for (PickCourse pickCours : pickCourses) {
                studentsMapper.insertChooseCourse(sId, pickCours.getcId());
            }
            return studentsMapper.getCourseByStudentsId(sId, course.getcTime(), course.getSemester());
        }
        return null;
    }
}

