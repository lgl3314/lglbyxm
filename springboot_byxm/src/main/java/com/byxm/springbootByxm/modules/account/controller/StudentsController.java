package com.byxm.springbootByxm.modules.account.controller;

import com.byxm.springbootByxm.modules.account.entity.*;
import com.byxm.springbootByxm.modules.account.service.CourseService;
import com.byxm.springbootByxm.modules.account.service.StudentsService;
import com.byxm.springbootByxm.modules.common.vo.Result;
import com.byxm.springbootByxm.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/by")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;
    @Autowired
    private CourseService courseService;

    /*
       1270.0.1:8080/by/toLogin
    */
    @PostMapping(value = "/toLogin/{np}")
    public String login(@PathVariable String np) {

        return studentsService.login(np);
    }

    /*
     * 127.0.0.1:8080/by/CourseByCId/1
     * 查看课表
     */
    @GetMapping("/CourseByCId/{cId}")
    public List<Course> getCourseByCId(@PathVariable int cId) {
        return studentsService.getCourseByCId(cId);
    }
    /*
     * 127.0.0.1:8080/by/getStudentById/1
     * 查看学费
     */
    @GetMapping("/getStudentById/{sId}")
    public List<Students> getStudentById(@PathVariable int sId){
        return studentsService.getStudentById(sId);
    }

    @PostMapping(value = "/achievements", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Students> getStudentsBySearchVo(@RequestBody SearchVo searchVo) {
        return studentsService.getStudentsBySearchVo(searchVo);
    }

    @PostMapping(value = "/TextBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<TextBooks> getTextBookBySearchVo(@RequestBody SearchVo searchVo) {
        return courseService.getTextBookBySearchVo(searchVo);
    }

    @PostMapping(value = "/Course", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Course> getCourseBySearchVo(@RequestBody SearchVo searchVo) {
        return courseService.getCourseBySearchVo(searchVo);
    }
    /*
       127.0.0.1:8080/by/course/1------------post
       {"cTime":"2020-2021","semester":"第一学期"}
       通过学生ID、指定年度和学期获取选课结果
    */
    @PostMapping("/course/{studentsId}")
    public List<Course> getCourseByStudentsId(@PathVariable int studentsId, @RequestBody Course course){
        return studentsService.getCourseByStudentsId(studentsId, course);
    }

    /*
        127.0.0.1:8080/by/pickCourse/1-----------post

        学生进行选课，返回本学期选课结果
     */
    @PostMapping("/pickCourse/{sId}")
    public List<Course> insertChooseCourse(@PathVariable int sId,
                                           @RequestBody List<PickCourse> pickCourses,
                                           @RequestBody Course course){
        return studentsService.insertChooseCourse(sId, pickCourses, course);
    }

    /*
        127.0.0.1:8080/by/courses-------------post
        {"cTime":"2017-2018","semester":"第一学期"}
        获取所有可选课程
     */
    @PostMapping(value = "/courses")
    public List<Course> getCourses(@RequestBody Course course){
        return studentsService.getCourses(course);
    }

    /*
        127.0.0.1:8080/by/student/1-------------get
        获取当前用户的学籍资料
     */
    @GetMapping("/student/{sId}")
    public Students getStudentBysId(@PathVariable int sId){
        return studentsService.getStudentBysId(sId);
    }

    /*
        127.0.0.1:8080/by/student-------------post
        {"accountNumber":"9527","password":"11111111"}
        原密码查询
     */
    @PostMapping("/student")
    public String getStudentBysIdAndPassword(@RequestBody Students students){
        return studentsService.getStudentBysIdAndPassword(students);
    }

    /*
        127.0.0.1:8080/by/student-------------put
        {"accountNumber":"9527","password":"123456"}
        修改密码
     */
    @PutMapping("/student")
    public String updateStudentByPassword(@RequestBody Students students){
        return studentsService.updateStudentByPassword(students);
    }
}
