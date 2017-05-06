package io.chrismajor.wlt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Christo on 06/05/2017.
 */
@Controller
public class ListController {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String greeting(
            @RequestParam(value="name", required=false, defaultValue="World") String name,
            Model model) {
        model.addAttribute("name", name);
        return "list";
    }
}
