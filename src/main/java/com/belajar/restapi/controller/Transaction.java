package com.belajar.restapi.controller;


import com.belajar.restapi.dto.TransactionDTO;
import com.belajar.restapi.dto.UsersDTO;
import com.belajar.restapi.service.TransactionService;
import com.belajar.restapi.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class Transaction {
    @Autowired
    TransactionService transactionService;
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ModelAndView transactionpost(TransactionDTO transaction, String username, RedirectAttributes redir) {
        log.info(transaction.getQty() + " qty "+ transaction.getIdHardware() + " id hardware");
        log.info(username);
        transaction.setUsername(username);
        ModelAndView mav = new ModelAndView("transaction");
        redir.addFlashAttribute("datatransaksi", transactionService.create(transaction) );
        mav.setViewName("redirect:/index");
        return mav;
    }
//    @RequestMapping(value = "profil/{username}", method = RequestMethod.GET)
//    public ModelAndView gettransaction(@PathVariable ("username") String username, RedirectAttributes redir) {
//        log.info(username);
//        ModelAndView mav = new ModelAndView("transaction");
//        mav.addObject("transactionData",transactionService.findByUsername(username));
//        return mav;
//    }
}
