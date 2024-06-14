package com.timothy.bongsamoa.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

@Controller
public class TKHomeController {
//    private static final Logger logger = (Logger)LoggerFactory.getLogger(TKHomeController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String display(Locale locale, Model model) {
//        logger.info("");

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("date", formattedDate);

        return "TKHomePage";
    }
}
