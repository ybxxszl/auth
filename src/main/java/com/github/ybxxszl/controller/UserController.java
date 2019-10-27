package com.github.ybxxszl.controller;

import com.github.ybxxszl.annotation.Ignore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Ignore
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(@RequestParam(value = "userAccount") String userAccount, @RequestParam(value = "userPassword") String userPassword) {

    }

    @Ignore
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register(@RequestParam(value = "userAccount") String userAccount, @RequestParam(value = "userPassword") String userPassword) {

    }

    @Ignore
    @RequestMapping(value = "/judge", method = RequestMethod.GET)
    public void judge(@RequestParam(value = "userAccount") String userAccount) {

    }

    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public void change(@RequestParam(value = "userId") String userId, @RequestParam(value = "userState") Integer userState) {

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(@RequestParam(value = "searchContent", required = false) String searchContent, @RequestParam(value = "userState", required = false) Integer userState,
                     @RequestParam(value = "beforeInsertTime", required = false) String beforeInsertTime, @RequestParam(value = "afterInsertTime", required = false) String afterInsertTime,
                     @RequestParam(value = "beforeUpdateTime", required = false) String beforeUpdateTime, @RequestParam(value = "afterUpdateTime", required = false) String afterUpdateTime) {

    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public void detail(@RequestParam(value = "userId") String userId) {

    }

}
