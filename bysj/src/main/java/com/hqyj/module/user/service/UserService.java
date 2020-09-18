package com.hqyj.module.user.service;

import com.hqyj.module.user.entity.User;

public interface UserService {
    void register(User user);

    boolean isUserConflict(String username);

    User findByUsername(String username);

    void active(Long id);
}
