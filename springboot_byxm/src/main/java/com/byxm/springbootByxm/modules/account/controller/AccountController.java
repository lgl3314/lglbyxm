package com.byxm.springbootByxm.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    /*
    *127.0.0.1:8080/account/login
    * */
    @GetMapping("/login")
    public String loginpage(){
        return "indexSimple";
    }

    /*
     *127.0.0.1:8080/account/changePassword
     * */
    @GetMapping("changePassword")
    public String changePasswordpage(){
        return "index";
    }

    /*
     *127.0.0.1:8080/account/pickcoursev
     * */
    @GetMapping("/pickcoursev")
    public String pickcoursevpage(){
        return "index";
    }

    /*
     *127.0.0.1:8080/account/evaluation
     * */
    @GetMapping("/evaluation")
    public String evaluationpage(){
        return "index";
    }

    /*
     *127.0.0.1:8080/account/information
     * */
    @GetMapping("/information")
    public String informationpage(){
        return "index";
    }

    /*
     *127.0.0.1:8080/account/achievements
     * */
    @GetMapping("/achievements")
    public String achievementspage(){
        return "index";
    }
 /*
     *127.0.0.1:8080/account/textbook
     * */
    @GetMapping("/textbook")
    public String textbookpage(){
        return "index";
    }


    @GetMapping("/adminClassTable")
    public String adminClassTablepage(){
        return "index";
    }

}
