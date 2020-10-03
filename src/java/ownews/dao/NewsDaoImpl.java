package ownews.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ownews.bean.Login;
import ownews.bean.News;
import ownews.bean.Relationship;
import ownews.bean.VotedPosts;

/**
 *
 * @author Shreya Appia
 * Provides the implementation of functions to get list of news, save posted news
 * in database, save rating details in database
 */
@Repository("NewsDao")
@SuppressWarnings("unchecked")
public class NewsDaoImpl implements NewsDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private RelationshipDao relationshipDao;

    @Override
    public List<News> getPopularNews() {
        List<News> news;
        List<News> top_news;
        news = sessionFactory.getCurrentSession().createCriteria(News.class).list();
        //sort the news items in descending order of popularity
        Collections.sort(news, new Comparator<News>() {
            @Override
            public int compare(News n1, News n2) {
                return new Double(n2.getPopularity()).compareTo(new Double(n1.getPopularity()));
            }
        });
        if (news.size() > 5) {
            top_news = news.subList(0, 5);
        } else {
            top_news = news;
        }
        return top_news;
    }

    @Override
    public List<News> getNewslinks(int currentUser) {
        //get list of friends of the current user
        List<Login> users = relationshipDao.getFriends(currentUser);
        int size = users.size();
        int friendid;
        List<News> news = new ArrayList<News>();
        List<News> newsitems;
        //get the news items each friend has posted
        for (int i = 0; i < size; i++) {
            friendid = users.get(i).getUserid();
            newsitems = sessionFactory.getCurrentSession().createCriteria(News.class)
                    .add(Restrictions.like("userid", friendid)).list();
            int newsitemsSize = newsitems.size();
            for (int j = 0; j < newsitemsSize; j++) {
                news.add(newsitems.get(j));
            }
        }
        //sort the news items in descending order of popularity
        Collections.sort(news, new Comparator<News>() {
            @Override
            public int compare(News n1, News n2) {
                return new Double(n2.getPopularity()).compareTo(new Double(n1.getPopularity()));
            }
        });
        return news;
    }

    @Override
    public List<VotedPosts> getVotedNewslinks(int currentUser) {
        List<News> userNewslinks = getNewslinks(currentUser);
        int userNews_size = userNewslinks.size();
        List<Integer> userNewsids = new ArrayList<Integer>();
        for (int i = 0; i < userNews_size; i++) {
            userNewsids.add(userNewslinks.get(i).getNewsid());
        }
        List<VotedPosts> votednews;
        if (userNews_size > 0) {
            votednews = sessionFactory.getCurrentSession().createCriteria(VotedPosts.class)
                    .add(Restrictions.conjunction().add(Restrictions.like("userid", currentUser))
                    .add(Restrictions.in("newsid", userNewsids))).list();
        } else {
            votednews = sessionFactory.getCurrentSession().createCriteria(VotedPosts.class)
                    .add(Restrictions.like("userid", currentUser)).list();
        }
        return votednews;
    }

    @Override
    public void postNews(int currentUser, News news) {
        double initialpopularity;
        int newslist_size = sessionFactory.getCurrentSession().createCriteria(News.class).list().size();
        if (newslist_size == 0) {
            initialpopularity = 5.0;
        } else {
            initialpopularity = getAveragePopularity();
        }
        int intialvotes = 0;
        news.setUserid(currentUser);
        news.setVotes(intialvotes);
        news.setPopularity(initialpopularity);
        sessionFactory.getCurrentSession().saveOrUpdate(news);
    }

    @Override
    public void saveVoteDetails(int currentUser, int currentNewsID, int currentRating) {
        VotedPosts votedposts = new VotedPosts();
        votedposts.setUserid(currentUser);
        votedposts.setNewsid(currentNewsID);
        votedposts.setRating(currentRating);
        sessionFactory.getCurrentSession().saveOrUpdate(votedposts);
    }

    @Override
    public void updateNewsItem(int currentUser, int currentNewsID, int currentRating) {
        //Defining constant value
        double maxRating = 10.0;
        //Get Known values from News table
        News currentnews = (News) sessionFactory.getCurrentSession().createCriteria(News.class)
                .add(Restrictions.like("newsid", currentNewsID)).uniqueResult();
        int newsVotes = currentnews.getVotes() + 1;
        int NewsPostedByUser = currentnews.getUserid();
        double currentPopularity = currentnews.getPopularity();
        Calendar postedtime = currentnews.getPostedtime();

        //Get known values from Relationship table
        Relationship relationship = (Relationship) sessionFactory.getCurrentSession()
                .createCriteria(Relationship.class)
                .add(Restrictions.conjunction().add(Restrictions.like("userid", currentUser))
                .add(Restrictions.like("friendid", NewsPostedByUser))).uniqueResult();
        //Relation value from database
        double currentRelation = relationship.getRelation();
        int RelationID = relationship.getRelationid();
        int relationVotes = relationship.getRelation_votes() + 1;

        //calculating age of news post
        Calendar timenow = Calendar.getInstance();
        double timeDiffInMillis = timenow.getTimeInMillis() - postedtime.getTimeInMillis();
        double ageInSeconds = timeDiffInMillis / 1000;

        double popularity;
        double relation;

        //Calculate new Popularity and Relationship values for Current Rating > 5
        if (currentRating > 5) {
            popularity = currentPopularity + ((currentRelation * currentRating * ((double) newsVotes / 100)) / (Math.pow(ageInSeconds, 0.1)));
            relation = currentRelation + (((double) currentRating / maxRating) * ((double) relationVotes / 100));
        } //Calculate new Popularity and Relationship values for Current Rating <= 5
        else {
            popularity = currentPopularity - ((currentRelation * (10 - currentRating) * ((double) newsVotes / 100)) / (Math.pow(ageInSeconds, 0.1)));
            relation = currentRelation - (((double) (10 - currentRating) / maxRating) * ((double) relationVotes / 100));
        }

        double average_popularity = getAveragePopularity();
        double threshold_popularity = 0.8 * average_popularity;

        double average_relation = relationshipDao.getAverageRelation();
        double threshold_relation = 0.4 * average_relation;

        //If popularity falls below threshold delete the news post else update popularity in database
        Session session = sessionFactory.getCurrentSession();
        if (popularity < threshold_popularity) {
            //Delete the news item
            String deleteqry1 = "DELETE News WHERE newsid=:newsid";
            Query dq1 = session.createQuery(deleteqry1);
            dq1.setParameter("newsid", currentNewsID);
            dq1.executeUpdate();
        } else {
            //Update votes and popularity in News table
            String updateqry1 = "UPDATE News set votes=:votes, popularity=:popularity, postedtime=:postedtime WHERE newsid=:newsid";
            Query q1 = session.createQuery(updateqry1);
            q1.setParameter("newsid", currentNewsID);
            q1.setParameter("votes", newsVotes);
            q1.setParameter("popularity", popularity);
            q1.setParameter("postedtime", postedtime);
            q1.executeUpdate();
        }

        //Update relation in relationship table
        int activerelationflag = 0;
        int brokenrelationflag = 1;
        String updateqry2 = "UPDATE Relationship set relation=:relation, relation_votes=:relation_votes, relation_flag=:relation_flag WHERE relationid=:relationid";
        Query q2 = session.createQuery(updateqry2);
        q2.setParameter("relation", relation);
        q2.setParameter("relation_votes", relationVotes);

        if (relation < threshold_relation) {
            q2.setParameter("relation_flag", brokenrelationflag);
        } else {
            q2.setParameter("relation_flag", activerelationflag);
        }

        q2.setParameter("relationid", RelationID);
        q2.executeUpdate();
    }

    @Override
    public void isSpammer_delete(int NewsPostedByUser) {
        Session session = sessionFactory.getCurrentSession();

        //Get average relationship value and calculate threshold relationship value
        double average_relation = relationshipDao.getAverageRelation();
        double threshold_relation = 0.4 * average_relation;

        //Delete the user who posted news if he is a spammer
        List<Relationship> isSpammerRelations = sessionFactory.getCurrentSession()
                .createCriteria(Relationship.class)
                .add(Restrictions.like("friendid", NewsPostedByUser)).list();
        int isSpammerRelations_size = isSpammerRelations.size();

        System.out.println("isSpammerRelations_size" + isSpammerRelations_size);

        //Calculate average relationship of NewsPostedByUser and get broken relations
        double relation_sum = 0;
        int brokenRelations_size = 0;
        for (int j = 0; j < isSpammerRelations_size; j++) {
            relation_sum += isSpammerRelations.get(j).getRelation();
            if (isSpammerRelations.get(j).getRelation_flag() == 1) {
                brokenRelations_size += 1;
            }
        }
        double isSpammer_averagerelation = relation_sum / isSpammerRelations_size;

        System.out.println("isSpammer_averagerelation" + isSpammer_averagerelation);

        if ((isSpammer_averagerelation < threshold_relation) || (brokenRelations_size == isSpammerRelations_size)) {
            String deleteSpammerQry = "DELETE Login WHERE userid=:userid";
            Query deleteSpammer = session.createQuery(deleteSpammerQry);
            deleteSpammer.setParameter("userid", NewsPostedByUser);
            deleteSpammer.executeUpdate();
        }
    }

    @Override
    public int getNewsPostedByUser(int currentNewsID) {
        //Get Known values from News table
        News currentnews = (News) sessionFactory.getCurrentSession().createCriteria(News.class)
                .add(Restrictions.like("newsid", currentNewsID)).uniqueResult();
        int NewsPostedByUser = currentnews.getUserid();
        return NewsPostedByUser;
    }

    @Override
    public double getAveragePopularity() {
        //Fetch all popularity values from database to calculate threshold popularity
        List<News> news_list = sessionFactory.getCurrentSession().createCriteria(News.class).list();
        int news_list_size = news_list.size();
        double total_popularity = 0;
        for (int i = 0; i < news_list_size; i++) {
            total_popularity += news_list.get(i).getPopularity();
        }
        double average_popularity = total_popularity / news_list_size;
        return average_popularity;
    }

    public int getTotalVotes() {
        //Fetch all popularity values from database to calculate threshold popularity
        List<News> news_list = sessionFactory.getCurrentSession().createCriteria(News.class).list();
        int news_list_size = news_list.size();
        int total_votes = 0;
        for (int i = 0; i < news_list_size; i++) {
            total_votes += news_list.get(i).getVotes();
        }

        return total_votes;
    }
}
