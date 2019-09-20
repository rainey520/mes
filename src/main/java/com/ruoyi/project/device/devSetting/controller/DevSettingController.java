package com.ruoyi.project.device.devSetting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/device/devSetting")
public class DevSettingController {

    private String prefix = "device/devSetting";

    @GetMapping("/{devId}")
    public String devSetting(@PathVariable("devId")int devId){
        System.out.println(devId);
        return  prefix +"/devSetting";
    }
}
