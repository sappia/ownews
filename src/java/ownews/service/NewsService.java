package ownews.service;

import java.util.List;
import ownews.bean.News;
import ownews.bean.VotedPosts;

/**
 *
 * @author Shreya Appia
 * Provides the interface for News Service functions
 */
public interface NewsService {

    public void updateUserId(int currentUser);

    public List<News> getNewslinks();

    public void postNews(News news);
    
    public void updateVoteDetails(int currentNewsID, int currentRating);

    public List<News> getPopularNews();

    public List<VotedPosts> getVotedNewslinks();
    
    public int getNewsPostedByUser(int currentNewsID);

    public void isSpammer_delete(int NewsPostedByUser);

}
