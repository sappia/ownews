package ownews.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ownews.bean.News;
import ownews.bean.VotedPosts;
import ownews.dao.NewsDao;

/**
 *
 * @author Shreya Appia
 * Provides the implementation of service functions which calls DAO functions from
 * News DAO for getting list of news links, saving posted news link or saving rating
 * details of a rated news
 */
@Service("NewsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NewsServiceImpl implements NewsService {

    private int currentUser;
    @Autowired
    private NewsDao newsDao;

    @Override
    public void updateUserId(int currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public List<News> getNewslinks() {
        return newsDao.getNewslinks(currentUser);
    }

    @Override
    public List<VotedPosts> getVotedNewslinks() {
        return newsDao.getVotedNewslinks(currentUser);
    }

    @Override
    public void postNews(News news) {
        newsDao.postNews(currentUser, news);
    }

    @Override
    public void updateVoteDetails(int currentNewsID, int currentRating) {
        newsDao.saveVoteDetails(currentUser, currentNewsID, currentRating);
        newsDao.updateNewsItem(currentUser, currentNewsID, currentRating);
    }

    @Override
    public int getNewsPostedByUser(int currentNewsID) {
        return newsDao.getNewsPostedByUser(currentNewsID);
    }
    
    @Override
    public void isSpammer_delete(int NewsPostedByUser) {
        newsDao.isSpammer_delete(NewsPostedByUser);
    }

    @Override
    public List<News> getPopularNews() {
        return newsDao.getPopularNews();
    }
}
