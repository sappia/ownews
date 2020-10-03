package ownews.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ownews.bean.Login;

/**
 *
 * @author Shreya Appia
 * Controller that handles requests specific to log in and log out functionalities
 */
@Controller
public class LoginController {
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView viewLogin(@ModelAttribute("login") Login login, BindingResult result) {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/loginSuccessful", method = RequestMethod.GET)
    public ModelAndView afterLogin(HttpServletRequest request) {
            return new ModelAndView("redirect:/viewnews.htm");
    }
    
    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public ModelAndView loginError(@ModelAttribute("login") Login login, BindingResult result) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("error", "true");
        return new ModelAndView("login", model);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView viewLogout(@ModelAttribute("login") Login login, BindingResult result) { 
        return new ModelAndView("login");
    }

}
