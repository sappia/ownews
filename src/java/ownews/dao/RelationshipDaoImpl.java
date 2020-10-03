package ownews.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ownews.bean.FriendRecommender;
import ownews.bean.Login;
import ownews.bean.News;
import ownews.bean.Relationship;
import ownews.bean.VotedPosts;

/**
 *
 * @author Shreya Appia
 * Provides the implementation of functions to get list of friends, save added 
 * friends in database
 */
@Repository("RelationshipDao")
@SuppressWarnings("unchecked")
public class RelationshipDaoImpl implements RelationshipDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private NewsDao newsDao;

    @Override
    public List<Login> getFriends(int currentUser) {
        int userId = currentUser;
        int active_relation = 0;
        //get all friends of the current user where relation is active
        List<Relationship> friends = (List<Relationship>) sessionFactory.getCurrentSession()
                .createCriteria(Relationship.class)
                .add(Restrictions.conjunction().add(Restrictions.like("userid", userId))
                .add(Restrictions.like("relation_flag", active_relation))).list();
        int size = friends.size();
        List<Login> users = new ArrayList<Login>();
        int friendid;
        // for each friend get the login details
        for (int i = 0; i < size; i++) {
            friendid = friends.get(i).getFriendid();
            Login user = (Login) sessionFactory.getCurrentSession().createCriteria(Login.class)
                    .add(Restrictions.like("userid", friendid)).uniqueResult();
            users.add(user);
        }
        return users;

    }

    @Override
    public void addFriend(int currentUser, Relationship relationship) {
        double initialRelation;
        int relationlist_size = sessionFactory.getCurrentSession().createCriteria(Relationship.class).list().size();
        if (relationlist_size == 0) {
            initialRelation = 0.5;
        } else {
            initialRelation = getAverageRelation();
        }
        int intialvotes = 0;
        int active_relation = 0;
        relationship.setUserid(currentUser);
        relationship.setRelation(initialRelation);
        relationship.setRelation_votes(intialvotes);
        relationship.setRelation_flag(active_relation);
        sessionFactory.getCurrentSession().saveOrUpdate(relationship);
    }

    @Override
    public int needMoreFriends(int currentUser) {
        int need;
        int newuser_threshold = 3;

        // Get count of total users in the system
        List<Login> users = sessionFactory.getCurrentSession().createCriteria(Login.class).list();
        int total_users = users.size();
        int percent_users = (int) ((5 / 100) * total_users);
        if (percent_users <= newuser_threshold) {
            percent_users = newuser_threshold;
        }
        //get all friends of the current user
        List<Relationship> friends = (List<Relationship>) sessionFactory.getCurrentSession().createCriteria(Relationship.class)
                .add(Restrictions.like("userid", currentUser)).
                list();
        int friend_size = friends.size();
        //Should the user be treated as new user?
        //check if user has posted any news items
        List<News> newsposted;
        newsposted = sessionFactory.getCurrentSession().createCriteria(News.class)
                .add(Restrictions.like("userid", currentUser)).list();
        int newsposted_size = newsposted.size();
        //check if user has voted for any news items
        List<VotedPosts> newsvoted = newsDao.getVotedNewslinks(currentUser);
        int newsvoted_size = newsvoted.size();

        if (((newsposted_size <= newuser_threshold) || (newsvoted_size <= newuser_threshold)) && (friend_size <= newuser_threshold)) {
            need = 2;
        } else if (friend_size <= percent_users) {
            need = 1;
        } else {
            need = 0;
        }
        return need;
    }

    @Override
    public List<Login> getFriendSuggestions(int currentUser, String suggestion) {
        // The user's friends should not be included in friend suggestions
        List<Login> users = getFriends(currentUser);
        int size = users.size();
        List<Integer> notids = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            notids.add(users.get(i).getUserid());
        }
        // The user should not be included in friend suggestions
        notids.add(currentUser);
        //Broken relations should not be included in friend suggestions
        int broken_relation = 1;
        //get all broken relations of the current user
        List<Relationship> broken_relationships = (List<Relationship>) sessionFactory.getCurrentSession()
                .createCriteria(Relationship.class)
                .add(Restrictions.conjunction().add(Restrictions.like("userid", currentUser))
                .add(Restrictions.like("relation_flag", broken_relation))).list();
        int b_rel_size = broken_relationships.size();
        //get all broken relations where current user is friend
        List<Relationship> broken_followers = (List<Relationship>) sessionFactory.getCurrentSession()
                .createCriteria(Relationship.class)
                .add(Restrictions.conjunction()
                .add(Restrictions.like("friendid", currentUser))
                .add(Restrictions.like("relation_flag", broken_relation))).list();
        int b_fol_size = broken_followers.size();
        int notfriendid;
        // for each broken relation get the login details of the ex-friend
        for (int i = 0; i < b_rel_size; i++) {
            notfriendid = broken_relationships.get(i).getFriendid();
            Login user = (Login) sessionFactory.getCurrentSession().createCriteria(Login.class)
                    .add(Restrictions.like("userid", notfriendid)).uniqueResult();
            notids.add(user.getUserid());
        }
        // for each broken relation get the login details of the ex-follower
        for (int i = 0; i < b_fol_size; i++) {
            notfriendid = broken_followers.get(i).getUserid();
            Login user = (Login) sessionFactory.getCurrentSession().createCriteria(Login.class)
                    .add(Restrictions.like("userid", notfriendid)).uniqueResult();
            notids.add(user.getUserid());
        }
        
        // Suggestion of friends
        List<Login> userSuggestions = sessionFactory.getCurrentSession().createCriteria(Login.class)
                .add(Restrictions.not(Restrictions.in("userid", notids))).list();

        // Friends following the user
        if (suggestion.equals("ufy")) {
            List<Login> Followers = getFollowers(currentUser, notids);
            return Followers;
        }
        // Trustworthy friends
        if (suggestion.equals("trust")) {
            List<Login> Trustworthy = getTrustworthyOrPopular(userSuggestions, suggestion);
            return Trustworthy;
        } // Popular friends
        else if (suggestion.equals("popular")) {
            List<Login> Popular = getTrustworthyOrPopular(userSuggestions, suggestion);
            return Popular;
        } // Friends of friends
        else if (suggestion.equals("fof")) {
            List<Login> FOF_suggestions = getFOF(users, notids);
            return FOF_suggestions;
        } else {
            return userSuggestions;
        }

    }

    public List<Login> getFollowers(int currentUser, List<Integer> notids) {
        int active_relation = 0;
        // Get all followers of the user with active relationship
        List<Relationship> followers_rel = (List<Relationship>) sessionFactory.getCurrentSession()
                .createCriteria(Relationship.class)
                .add(Restrictions.conjunction()
                .add(Restrictions.like("friendid", currentUser))
                .add(Restrictions.like("relation_flag", active_relation))).list();
        
        int size = followers_rel.size();
        List<Login> followers = new ArrayList<Login>();
        int followerid;
        // for each follower get the login details
        for (int i = 0; i < size; i++) {
            followerid = followers_rel.get(i).getUserid();
            Login user = (Login) sessionFactory.getCurrentSession().createCriteria(Login.class)
                    .add(Restrictions.like("userid", followerid)).uniqueResult();
            if (!(notids.contains(user.getUserid()))) {
                followers.add(user);
            }
        }

        return followers;
    }

    public List<Login> getFOF(List<Login> users, List<Integer> notids) {
        int size = users.size();

        List<Integer> ids_to_add = new ArrayList<Integer>();
        List<Login> FOF_Suggestions = new ArrayList<Login>();
        for (int a = 0; a < size; a++) {
            int friendId = users.get(a).getUserid();
            List<Login> Frnds_to_add = getFriends(friendId);
            for (int b = 0; b < Frnds_to_add.size(); b++) {
                int id = Frnds_to_add.get(b).getUserid();
                if (!(notids.contains(id))) {
                    if (!(ids_to_add.contains(id))) {
                        ids_to_add.add(id);
                    }
                }
            }
        }
        int fof_size = ids_to_add.size();
        for (int i = 0; i < fof_size; i++) {
            int fofid = ids_to_add.get(i);
            Login fof = (Login) sessionFactory.getCurrentSession().createCriteria(Login.class)
                    .add(Restrictions.like("userid", fofid)).uniqueResult();
            FOF_Suggestions.add(fof);
        }

        return FOF_Suggestions;
    }

    public List<Login> getTrustworthyOrPopular(List<Login> userSuggestions, String suggestion) {
        int sugg_size = userSuggestions.size();
        List<FriendRecommender> recommendation = new ArrayList<FriendRecommender>();
        for (int j = 0; j < sugg_size; j++) {
            int userId = userSuggestions.get(j).getUserid();
            // Trust Scores of each friend
            List<Relationship> userRelations = (List<Relationship>) sessionFactory.getCurrentSession()
                    .createCriteria(Relationship.class)
                    .add(Restrictions.like("friendid", userId)).list();
            int rel_size = userRelations.size();
            double trust_sum = 0;
            for (int k = 0; k < rel_size; k++) {
                trust_sum = trust_sum + userRelations.get(k).getRelation();
            }

            // Popularity Score of each friend
            List<News> userNews = (List<News>) sessionFactory.getCurrentSession()
                    .createCriteria(News.class)
                    .add(Restrictions.like("userid", userId)).list();
            int news_size = userNews.size();
            double popularity_sum = 0;
            for (int k = 0; k < news_size; k++) {
                popularity_sum = popularity_sum + userNews.get(k).getPopularity();
            }

            Login user = (Login) sessionFactory.getCurrentSession().createCriteria(Login.class)
                    .add(Restrictions.like("userid", userId)).uniqueResult();
            FriendRecommender frnd = new FriendRecommender();
            frnd.setLogin(user);
            frnd.setTrust_score(trust_sum);
            frnd.setPopularity_score(popularity_sum);
            recommendation.add(frnd);
        }
        List<Login> Friend_Suggestions = new ArrayList<Login>();
        if (suggestion.equals("trust")) {
            //sort the friend suggestions in descending order of trustscores
            Collections.sort(recommendation, new Comparator<FriendRecommender>() {
                @Override
                public int compare(FriendRecommender t1, FriendRecommender t2) {
                    return new Double(t2.getTrust_score()).compareTo(new Double(t1.getTrust_score()));
                }
            });
            for (int l = 0; l < sugg_size; l++) {
                Friend_Suggestions.add(recommendation.get(l).getLogin());
            }
        } else if (suggestion.equals("popular")) {
            //sort the friend suggestions in descending order of popularityscores
            Collections.sort(recommendation, new Comparator<FriendRecommender>() {
                @Override
                public int compare(FriendRecommender t1, FriendRecommender t2) {
                    return new Double(t2.getPopularity_score()).compareTo(new Double(t1.getPopularity_score()));
                }
            });

            for (int l = 0; l < sugg_size; l++) {
                Friend_Suggestions.add(recommendation.get(l).getLogin());
            }
        }
        return Friend_Suggestions;
    }

    @Override
    public double getAverageRelation() {
        //Fetch all relationship values from database to calculate threshold relationship
        List<Relationship> relation_list = sessionFactory.getCurrentSession().createCriteria(Relationship.class).list();
        int relation_list_size = relation_list.size();
        double total_relation = 0;
        for (int j = 0; j < relation_list_size; j++) {
            total_relation += relation_list.get(j).getRelation();
        }
        double average_relation = total_relation / relation_list_size;
        return average_relation;

    }
}
