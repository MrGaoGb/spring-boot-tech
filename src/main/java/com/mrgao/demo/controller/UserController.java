package com.mrgao.demo.controller;

import com.mrgao.demo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Gao
 * @date 2023/3/30 15:25
 * @apiNote:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public List<User> list() {
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().userName("张三").password("123456").age(23).build());
        userList.add(User.builder().userName("王五").password("000456").age(25).build());
        return userList;
    }
}
