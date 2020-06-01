package com.ago.custom.CustomInterceptor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:CustomInterceptorController
 * @Describe:
 * @Data:2020/5/2614:48
 * @Author:Ago
 * @Version 1.0
 */
@RestController
@RequestMapping("/interceptor")
public class CustomInterceptorController {

    @RequestMapping("oldLogin")
    public void testOldLogin(){
        System.out.println("old login method");
    }


    @RequestMapping("newLogin")
    public String testNewLogin(){
        System.out.println("new login method");
        return "new login method";
    }

}
