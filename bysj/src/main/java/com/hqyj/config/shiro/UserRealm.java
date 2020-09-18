package com.hqyj.config.shiro;

import com.hqyj.module.user.entity.User;
import com.hqyj.module.user.service.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author lykgogo
 * @Date 2020/9/10 09:07
 */

@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证(登录)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
        //拿到用户名，密码
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);

        if (user==null) {
            throw new UnknownAccountException();
        }
        //匹配密码，交给shiro判断
        return new SimpleAuthenticationInfo(user,user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),this.getName());
    }
}
