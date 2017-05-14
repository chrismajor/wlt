package io.chrismajor.wlt.ui.controller;

import io.chrismajor.wlt.domain.User;
import io.chrismajor.wlt.service.SecurityService;
import io.chrismajor.wlt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Christo on 11/05/2017.
 */
@Controller
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        // TODO: create UI bean for user details
        model.addAttribute("userForm", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, /*BindingResult bindingResult, */Model model) {
        /* TODO: implement validation

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        */
        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/list";
    }
}
