package com.sayeu.shortenurl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sayeu.shortenurl.domain.DataItem;
import com.sayeu.shortenurl.services.DataService;

@Controller
public class ShortcutController {

    private DataService service;

    @Autowired
    public void setService(DataService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{shortcut}")
    public ModelAndView handle(@PathVariable String shortcut) {
        DataItem item = this.service.get(shortcut);
        String s="";
        try {
        	s = item.getUrl();
        }
        catch(java.lang.NullPointerException e)
        {
        	s = "";
        }
        
        return new ModelAndView("redirect:" + s);
    }

}
