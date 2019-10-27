package com.github.ybxxszl.controller;

import com.github.ybxxszl.annotation.Ignore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Ignore
@RestController
@RequestMapping(value = "/designTemplet")
public class DesignTempletController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create() {

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(@RequestParam(value = "designTempletId") String designTempletId) {

    }

    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public void publish(@RequestParam(value = "designTempletId") String designTempletId) {

    }

    @RequestMapping(value = "/abort", method = RequestMethod.GET)
    public void abort(@RequestParam(value = "designTempletId") String designTempletId) {

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(@RequestParam(value = "userId", required = false) String userId, @RequestParam(value = "state", required = false) Integer state,
                     @RequestParam(value = "userCode", required = false) String userCode, @RequestParam(value = "userName", required = false) String userName,
                     @RequestParam(value = "designTempletCode", required = false) String designTempletCode, @RequestParam(value = "designTempletName", required = false) String designTempletName,
                     @RequestParam(value = "beforeFinishTime", required = false) String beforeFinishTime, @RequestParam(value = "afterFinishTime", required = false) String afterFinishTime) {

    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public void detail(@RequestParam(value = "designTempletId") String designTempletId) {

    }

}
