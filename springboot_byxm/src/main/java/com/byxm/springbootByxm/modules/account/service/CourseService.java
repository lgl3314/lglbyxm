package com.byxm.springbootByxm.modules.account.service;

import com.byxm.springbootByxm.modules.account.entity.Course;
import com.byxm.springbootByxm.modules.account.entity.TextBooks;
import com.byxm.springbootByxm.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseService {
    List<Course> getCourseByCId(int cId);

    PageInfo<TextBooks> getTextBookBySearchVo(SearchVo searchVo);

    PageInfo<Course> getCourseBySearchVo(SearchVo searchVo);
}
