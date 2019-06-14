package com.belajar.restapi.controller;

import com.belajar.restapi.dto.UsersDTO;
import com.belajar.restapi.service.HardwareService;
import com.belajar.restapi.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class WebController {
    @Autowired
    HardwareService hardwareService;
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView allproduct(@CookieValue(value = "username",
            defaultValue = "defaultCookieValue")
                                               String cookieValue) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("username",cookieValue);
        mav.addObject("hardware", hardwareService.findAll());
        return mav;
    }
    @RequestMapping(value = "product/{id}", method = RequestMethod.GET)
    public ModelAndView detailproduct(@PathVariable("id")Integer id, @CookieValue(value = "username",
            defaultValue = "defaultCookieValue")
            String cookieValue) {
        ModelAndView mav = new ModelAndView("product");
        mav.addObject("username",cookieValue);
        mav.addObject("hardware", hardwareService.findById(id));
        return mav;
    }

}
