import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=355 lang=java
 *
 * [355] 设计推特
 *
 * https://leetcode-cn.com/problems/design-twitter/description/
 *
 * algorithms
 * Medium (36.74%)
 * Likes:    87
 * Dislikes: 0
 * Total Accepted:    9K
 * Total Submissions: 22K
 * Testcase Example:  '["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed","unfollow","getNewsFeed"]\n[[],[1,5],[1],[1,2],[2,6],[1],[1,2],[1]]'
 *
 * 
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 * 
 * 
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId):
 * 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 * 
 * 
 * 示例:
 * 
 * 
 * Twitter twitter = new Twitter();
 * 
 * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 * twitter.postTweet(1, 5);
 * 
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * twitter.getNewsFeed(1);
 * 
 * // 用户1关注了用户2.
 * twitter.follow(1, 2);
 * 
 * // 用户2发送了一个新推文 (推文id = 6).
 * twitter.postTweet(2, 6);
 * 
 * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 * twitter.getNewsFeed(1);
 * 
 * // 用户1取消关注了用户2.
 * twitter.unfollow(1, 2);
 * 
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * // 因为用户1已经不再关注用户2.
 * twitter.getNewsFeed(1);
 * 
 * 
 */

// @lc code=start
class Twitter {
    private static final int NEWS_FEED_MAX = 10;
    private static int timestamp = 0;

    private Map<Integer, User> usersMap;

    private class User {
        private int id;
        private HashSet<Integer> followees;
        private Tweet tweet_head;

        public User(int id) {
            this.id = id;
            this.followees = new HashSet<>();
            follow(id);
            this.tweet_head = null;
        }

        public void follow(int followeeId) {
            followees.add(followeeId);
        }

        public void unfollow(int followeeId) {
            followees.remove(followeeId);
        }

        public void post(int tweetId) {
            Tweet new_tweet = new Tweet(tweetId);
            new_tweet.next = tweet_head;
            tweet_head = new_tweet;
        }
    }

    private class Tweet {
        int id;
        int time;
        Tweet next;

        public Tweet(int id) {
            this.id = id;
            this.time = timestamp++;
        }
    }

    /** Initialize your data structure here. */
    public Twitter() {
        usersMap = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!usersMap.containsKey(userId)) {
            usersMap.put(userId, new User(userId));
        }
        usersMap.get(userId).post(tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new LinkedList<>();

        if (!usersMap.containsKey(userId)) return newsFeed;

        Set<Integer> followees = usersMap.get(userId).followees;
        PriorityQueue<Tweet> q = new PriorityQueue<>(followees.size(), (a, b)->(b.time - a.time));
        for (Integer followeeId : followees) {
            Tweet tweet_head = usersMap.get(followeeId).tweet_head;
            if (tweet_head != null) {
                q.add(tweet_head);
            }
        }

        int n = 0;
        while (!q.isEmpty() && n < NEWS_FEED_MAX) {
            Tweet t = q.poll();
            newsFeed.add(t.id);
            n++;
            if (t.next != null) {
                q.add(t.next);
            }
        }

        return newsFeed;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!usersMap.containsKey(followerId)) {
            usersMap.put(followerId, new User(followerId));
        }
        if (!usersMap.containsKey(followeeId)) {
            usersMap.put(followeeId, new User(followeeId));
        }
        usersMap.get(followerId).follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!usersMap.containsKey(followerId) || followerId == followeeId) {
            return ;
        }
        usersMap.get(followerId).unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
// @lc code=end

