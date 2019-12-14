package pl.coderslab.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {


    @RequestMapping(path = "/")
    public String homePage(Model model){
        return "index";
    }
}
