package io.chrismajor.wlt.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Christo on 11/05/2017.
 */
@Controller
public class SecurityController {

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("login attempt");
        return "login";
    }
}
