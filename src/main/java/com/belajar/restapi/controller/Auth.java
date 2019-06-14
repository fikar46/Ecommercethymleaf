package com.belajar.restapi.controller;

import com.belajar.restapi.dto.UsersDTO;
import com.belajar.restapi.service.HardwareService;
import com.belajar.restapi.service.UsersService;
import com.belajar.restapi.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class Auth {

    @Autowired
    UsersService usersService;


    @RequestMapping(value = "login", method = RequestMethod.GET) /*Komunikasi API dengan jenis POST*/
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
    @RequestMapping(value = "login", method = RequestMethod.POST) /*Komunikasi API dengan jenis POST*/
    public ModelAndView processlogin(UsersDTO user, String cookieValue, Model model, RedirectAttributes redir, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.setViewName("redirect:/index");
        model.addAttribute("cookieValue", cookieValue);
        redir.addFlashAttribute("userdata", usersService.findByUsernameAndPassword(user.getUsername(),user.getPassword()) );
        Cookie username = new Cookie("username", user.getUsername());
        log.info("${userdata.id}");
        username.setMaxAge(24 * 60 * 60);
        response.addCookie(username);
        redir.addFlashAttribute("user", user.getUsername());

        return mav;
    }
}
