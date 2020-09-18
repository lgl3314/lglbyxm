package com.byxm.springbootByxm.modules.account.service;


import com.byxm.springbootByxm.modules.account.entity.Role;

public interface RoleService {

    Role getRoleByStudentsId(int sId);
}
