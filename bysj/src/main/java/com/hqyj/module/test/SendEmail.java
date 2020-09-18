package com.hqyj.module.test;

import com.hqyj.utils.MailUtils;
import org.junit.Test;

public class SendEmail {
    @Test
    public void sendEmail(){
        MailUtils.sendMail("2313458226@qq.com","测试","测试邮件");
    }
}
