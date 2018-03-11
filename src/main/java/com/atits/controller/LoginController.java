package com.atits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @ResponseBody
    @RequestMapping(value = "/login/{id}", method = RequestMethod.GET)
    public String login(@PathVariable("id") Integer id) {
        System.out.println(id);
        return "hello";

    }

}



