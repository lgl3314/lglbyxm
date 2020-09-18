package com.hqyj.module.user.controller;

import com.hqyj.module.user.entity.User;
import com.hqyj.module.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.portable.UnknownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(User user) {
        userService.register(user);
        return "anon/register_success.html";
    }
    @GetMapping("/toRegister")
    public String toRegister(){
        return "anon/register.html";
    }

    @PostMapping("/isUserConflict")
    @ResponseBody
    public HashMap<Object, Object> isUserConflict(String username) {
        boolean flag = userService.isUserConflict(username);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("flag",flag);
        map.put("msg","查重");
        return map;
    }

    @GetMapping("/active/{id}")
    @ResponseBody
    public String active(@PathVariable Long id){
        userService.active(id);
        return "恭喜你，已成功激活。";
    }
    @PostMapping("/login")
    public String login(User user, Model model){
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(user.getUsername(),user.getPassword()));
            return "index";
        } catch (UnknownException e) {
            model.addAttribute("msg","用户名不存在");

        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
        }
        return "login";
    }

    @GetMapping("/tologin")
    public String tologin(){
        return "login";
    }

}
