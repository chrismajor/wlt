package io.chrismajor.wlt.ui.controller;

import io.chrismajor.wlt.service.SecurityService;
import io.chrismajor.wlt.service.UserService;
import io.chrismajor.wlt.ui.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller for security operations
 */
@Controller
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Initialise data binder for date formatting
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * Display the login page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {

        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "login";
    }

    /**
     * Display the registration page
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("userdetails", new UserDetails());
        return "register";
    }

    /**
     * Register the user
     * Unless there's validation issues, in which case punt them back to the forn
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @Valid @ModelAttribute("userdetails") UserDetails userDetails,
            BindingResult bindingResult,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        log.debug("registering user :: " + userDetails);

        // save the user's details
        userService.save(userDetails);

        // log the user in
        securityService.autologin(userDetails.getUsername(), userDetails.getPasswordConfirm());
        log.info("user successfully registered! " + userDetails);

        // direct the user to the list view
        return "redirect:/list";
    }
}
