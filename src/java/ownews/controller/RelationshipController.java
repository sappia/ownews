package ownews.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ownews.bean.AddFriendForm;
import ownews.bean.FriendSuggestions;
import ownews.bean.Login;
import ownews.bean.Relationship;
import ownews.service.RelationshipService;

/**
 *
 * @author Shreya Appia
 * Controller that handles requests specific to adding and viewing friends
 */
@Controller
public class RelationshipController {

    @Autowired
    private RelationshipService relationshipService;

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public ModelAndView viewFriends(@ModelAttribute("addfriendform") AddFriendForm addfriendform, BindingResult result, Principal principal) {
        String currentUser = principal.getName();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("currentUser", currentUser);
        model.put("yourfriends", relationshipService.getFriends());
        return new ModelAndView("friends", model);
    }

    @RequestMapping(value = "/friends", method = RequestMethod.POST)
    public ModelAndView addFriend(@ModelAttribute("addfriendform") AddFriendForm addfriendform, BindingResult result) {
        Relationship relationship = addfriendform.getRelationship();
        relationshipService.addFriend(relationship);
        return new ModelAndView("redirect:/friends.htm");
    }

    @ModelAttribute("suggestions")
    public List<FriendSuggestions> populateFriendSuggestions() {
        List<FriendSuggestions> suggestions = new ArrayList<FriendSuggestions>();
        suggestions.add(new FriendSuggestions("Users Following You", "ufy"));
        suggestions.add(new FriendSuggestions("Friends of Friends", "fof"));
        suggestions.add(new FriendSuggestions("By Popularity", "popular"));
        suggestions.add(new FriendSuggestions("By Trustworthiness", "trust"));
        return suggestions;
    }

    @RequestMapping(value = "friends/getfrnds", method = RequestMethod.GET, headers = "Accept=*/*")
    public @ResponseBody List<Login> getFrndSugg(AddFriendForm addfriendform, HttpServletRequest request, HttpServletResponse response) {
        String suggestion = request.getParameter("suggName");
        List<Login> suggestions = relationshipService.getFriendSuggestions(suggestion);
        return suggestions;
    }
}
