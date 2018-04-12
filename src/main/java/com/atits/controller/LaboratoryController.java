package com.atits.controller;

import com.atits.entity.Laboratory;
import com.atits.entity.User;
import com.atits.service.LaboratoryService;
import com.atits.entity.System;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class LaboratoryController {

    @Resource
    private LaboratoryService laboratoryService;

    @ResponseBody
    @RequestMapping(value = "/lab", method = RequestMethod.GET)
    public String save(){
        System system=new System();
        system.setId(1);
        system.setContent("this is a systemContent");
        system.setOverView("this is a systemContent");
        system.setSystemName("fristSystem");

        java.lang.System.out.println("controller");


        Laboratory laboratory=new Laboratory();
        laboratory.setCompany("abcdECompany");
        laboratory.setContent("this is a labContent");
        laboratory.setLabName("fristLab");
        laboratory.setState(1);
        laboratory.setTime("04-07");
        laboratory.setSystem(system);
        laboratory.setLabName("labname");
        laboratoryService.save(laboratory);
        return ""+laboratory.getSystem();
    }

    @RequestMapping(value = "/labFindall" ,method = RequestMethod.GET)
    @ResponseBody
    public List<Laboratory> findAll(){
        return laboratoryService.findAll();
    }

}
