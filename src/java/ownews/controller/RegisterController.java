package ownews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ownews.bean.Register;
import ownews.service.RegisterService;

/**
 *
 * @author Shreya Appia
 * Controller that handles requests specific to registration of new users
 */

@Controller
public class RegisterController {
    
    @Autowired
    private RegisterService registerService;
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView viewRegister(@ModelAttribute("register") Register register, BindingResult result) {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("register") Register register, BindingResult result) {
        boolean success = registerService.addUser(register);
        if (success) {
            return new ModelAndView("redirect:/registered.htm");
        } else {
            return new ModelAndView("redirect:/registerfailed.htm");
        }
    }

    @RequestMapping(value = "/registerfailed", method = RequestMethod.GET)
    public ModelAndView registerFailed() {
        return new ModelAndView("registerfailed");
    }

    @RequestMapping(value = "/registered", method = RequestMethod.GET)
    public ModelAndView viewRegistered() {
        return new ModelAndView("registered");
    }
}
