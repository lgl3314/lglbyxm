package com.byxm.springbootByxm.modules.account.service.impl;

import com.byxm.springbootByxm.modules.account.entity.Managers;
import com.byxm.springbootByxm.modules.account.mapper.ManagerMapper;
import com.byxm.springbootByxm.modules.account.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Managers getManagerBymName(String name) {
        return managerMapper.getManagerBymName(name);
    }
}
