package ownews.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ownews.bean.News;
import ownews.bean.Rating;
import ownews.bean.VotingForm;
import ownews.service.LoginService;
import ownews.service.NewsService;
import ownews.service.RelationshipService;

/**
 *
 * @author Shreya Appia
 * Controller that handles requests specific to view, rate and post news functionalities
 */
@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RelationshipService relationshipService;

    @RequestMapping(value = "/viewnews", method = RequestMethod.GET)
    public ModelAndView viewNewsPage(@ModelAttribute("votingform") VotingForm votingform, BindingResult result, Principal principal) {
        // get user ID & send it to the newsService and relationshipService
        String currentUser = principal.getName();
        int currentUserId = loginService.getUserIdFromName(currentUser);
        newsService.updateUserId(currentUserId);
        relationshipService.updateUserId(currentUserId);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("needfriends", relationshipService.needMoreFriends());
        model.put("vnews", newsService.getNewslinks());
        model.put("votednews", newsService.getVotedNewslinks());
        model.put("currentUser", currentUser);
        return new ModelAndView("viewnews", model);
    }

    @ModelAttribute("ratings")
    public List<Rating> populateRatingValues() {
        List<Rating> ratings = new ArrayList<Rating>();
        ratings.add(new Rating("1", 1));
        ratings.add(new Rating("2", 2));
        ratings.add(new Rating("3", 3));
        ratings.add(new Rating("4", 4));
        ratings.add(new Rating("5", 5));
        ratings.add(new Rating("6", 6));
        ratings.add(new Rating("7", 7));
        ratings.add(new Rating("8", 8));
        ratings.add(new Rating("9", 9));
        ratings.add(new Rating("10", 10));
        return ratings;
    }

    @RequestMapping(value = "/viewnews", method = RequestMethod.POST)
    public ModelAndView updateVotes(@ModelAttribute("votingform") VotingForm votingform, BindingResult result) {
        News news = votingform.getNews();
        int currentNewsID = news.getNewsid();
        int NewsPostedByUser = newsService.getNewsPostedByUser(currentNewsID);
        Rating rating = votingform.getRating();
        int currentRating = rating.getRatingValue();
        newsService.updateVoteDetails(currentNewsID, currentRating);
        newsService.isSpammer_delete(NewsPostedByUser);
        return new ModelAndView("redirect:/viewnews.htm");
    }

    @RequestMapping(value = "/postnews", method = RequestMethod.GET)
    public ModelAndView postNewsPage(@ModelAttribute("news") News news, BindingResult result, Principal principal) {
        String currentUser = principal.getName();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("currentUser", currentUser);
        return new ModelAndView("postnews", model);
    }

    @RequestMapping(value = "/postnews", method = RequestMethod.POST)
    public ModelAndView postNews(@ModelAttribute("news") News news, BindingResult result) {
        newsService.postNews(news);
        return new ModelAndView("redirect:/postnews.htm");
    }

    @RequestMapping(value = "/popularnews", method = RequestMethod.GET)
    public ModelAndView popularNewsPage(@ModelAttribute("news") News news, BindingResult result, Principal principal) {
        String currentUser = principal.getName();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("currentUser", currentUser);
        model.put("popularnews", newsService.getPopularNews());
        return new ModelAndView("popularnews", model);
    }
}