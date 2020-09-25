package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RunWith(SpringRunner.class)
public class HelloController extends BaseController {

    @Autowired
    private  IUserService userService;

    @RequestMapping("/index")
    public String hello(){
        return "index-";
    }

    @Test
    public void addUser() throws Exception {
        TestGenericity<String> genericity = new TestGenericity<>();
        String str = genericity.getVal("name");
        TestGenericity<Integer> ints = new TestGenericity<>();
        Integer num = ints.getVal(5);
        System.out.println(str + num);
    }
}
