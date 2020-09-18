package com.hqyj.module.user.service.impl;


import com.hqyj.module.user.entity.User;
import com.hqyj.module.user.mapper.UserMapper;
import com.hqyj.module.user.service.UserService;
import com.hqyj.module.userRole.entity.UserRole;
import com.hqyj.module.userRole.mapper.UserRoleMapper;
import com.hqyj.utils.MailUtils;
import com.hqyj.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public void register(User user) {
        user.setStatus(0);
        String salt = SaltUtils.getSalt(10);
        String md5Pwd = new Md5Hash(user.getPassword(), salt, 1024).toHex();
        user.setPassword(md5Pwd);
        user.setSalt(salt);
        userMapper.insertSelective(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(2);
        userRoleMapper.insertSelective(userRole);
        MailUtils.sendMail(user.getEmail(), "<a href='http://127.0.0.1:8080/user/active/"
                +user.getUserId()+"'>你正在注册xxxx系统，点击此链接以激活账户</a>", "激活邮件");
    }

    @Override
    public boolean isUserConflict(String username) {
        User user = new User();
        user.setUsername(username);
        int count = userMapper.selectCount(user);
        if (count==0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User findByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.selectOne(user);
    }

    @Override
    public void active(Long id) {
        User user=new User();
        user.setUserId(id);
        user.setStatus(1);
        userMapper.updateByPrimaryKeySelective(user);
    }
}

