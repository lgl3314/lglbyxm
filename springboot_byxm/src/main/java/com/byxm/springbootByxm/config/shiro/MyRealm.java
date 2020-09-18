
package com.byxm.springbootByxm.config.shiro;

import com.byxm.springbootByxm.modules.account.entity.Managers;
import com.byxm.springbootByxm.modules.account.entity.Students;
import com.byxm.springbootByxm.modules.account.entity.Teachers;
import com.byxm.springbootByxm.modules.account.service.ManagerService;
import com.byxm.springbootByxm.modules.account.service.StudentsService;
import com.byxm.springbootByxm.modules.account.service.TeachersService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private StudentsService studentsService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private TeachersService teachersService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name = (String) authenticationToken.getPrincipal();

        //        管理员进入
        if ('m' == (name.charAt(0)) || 'M' == (name.charAt(0))) {
            Managers managers = managerService.getManagerBymName(name);
            if (managers == null) {
                throw new UnknownAccountException("该用户不存在");
            }
            return new SimpleAuthenticationInfo(managers, managers.getPassword(), getName());
        }

        //        教师进入
        else if ('t' == (name.charAt(0)) || 'T' == (name.charAt(0))) {
            Teachers teachers = teachersService.getTeachersBytName(name);
            if (teachers == null) {
                throw new UnknownAccountException("该用户不存在");
            }
            return new SimpleAuthenticationInfo(teachers, teachers.getPassword(), getName());
        }

        //        学生进入
        else {
            Students student = studentsService.getStudentsByAccountNumber(name);
            if (student == null) {
                throw new UnknownAccountException("该用户不存在");
            }
            return new SimpleAuthenticationInfo(student, student.getPassword(), getName());
        }
    }

}


