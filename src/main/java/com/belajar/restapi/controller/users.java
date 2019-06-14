package com.belajar.restapi.controller;

import com.belajar.restapi.dto.UsersDTO;
import com.belajar.restapi.service.HardwareService;
import com.belajar.restapi.service.TransactionService;
import com.belajar.restapi.service.UsersService;
import com.belajar.restapi.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
public class users {

    @Autowired
    UsersService usersService;
    @Autowired
    TransactionService transactionService;


    @RequestMapping(value = "profil/{username}", method = RequestMethod.GET) /*Komunikasi API dengan jenis POST*/
    public ModelAndView profilpage(@PathVariable ("username") String username, @CookieValue(value = "username",
            defaultValue = "defaultCookieValue")
                                                String cookieValue, RedirectAttributes redir) {
        ModelAndView mav = new ModelAndView("profil");
        mav.addObject("userdata",usersService.findByUsername(username));
        mav.addObject("username",cookieValue);
        mav.addObject("transactionData",transactionService.findByUsername(username));
        return mav;
    }

}
