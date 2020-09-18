package com.byxm.springbootByxm.modules.account.service;

import com.byxm.springbootByxm.modules.account.entity.Managers;

public interface ManagerService {

    Managers getManagerBymName(String name);
}
