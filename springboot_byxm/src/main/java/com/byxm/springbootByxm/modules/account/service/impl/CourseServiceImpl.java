package com.byxm.springbootByxm.modules.account.service.impl;

import com.byxm.springbootByxm.modules.account.entity.Course;
import com.byxm.springbootByxm.modules.account.entity.TextBooks;
import com.byxm.springbootByxm.modules.account.mapper.CourseMapper;
import com.byxm.springbootByxm.modules.account.service.CourseService;
import com.byxm.springbootByxm.modules.common.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getCourseByCId(int cId) {
        return courseMapper.getCourseByCId(cId);
    }

    @Override
    public PageInfo<TextBooks> getTextBookBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<TextBooks>(Optional
                .ofNullable(courseMapper.getTextBookBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }



    @Override
    public PageInfo<Course> getCourseBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<Course>(Optional
                .ofNullable(courseMapper.getCourseBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }
}
