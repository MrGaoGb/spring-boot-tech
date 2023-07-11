package com.mrgao.demo.controller;

import com.mrgao.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Gao
 * @date 2023/3/30 15:25
 * @apiNote:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/list",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<User> list() {
        log.info("###REQ###");
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().userName("张三").password("123456").age(23).build());
        userList.add(User.builder().userName("王五").password("000456").age(25).build());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
}
