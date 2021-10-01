package com.icarros.formula1.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class DocumentationController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
