package ownews.dao;

import java.util.List;
import ownews.bean.News;
import ownews.bean.VotedPosts;

/**
 *
 * @author Shreya Appia
 * Provides the interface for News DAO functions
 */
public interface NewsDao {

    public List<News> getNewslinks(int currentUser);

    public void postNews(int currentUser, News news);
            
    public void updateNewsItem(int currentUser, int currentNewsID, int currentRating);
    
    public void saveVoteDetails(int currentUser, int currentNewsID, int currentRating);
    
    public void isSpammer_delete(int NewsPostedByUser);

    public List<News> getPopularNews();

    public int getNewsPostedByUser(int currentNewsID);
    
    public List<VotedPosts> getVotedNewslinks(int currentUser);
    
    public double getAveragePopularity();

}
