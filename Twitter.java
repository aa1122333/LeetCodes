package leetcodeTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Twitter {
	private final Map<Integer, LinkedList<Tweet>> feeds = new HashMap<>(); 
	private final Map<Integer, Set<Integer>> relations = new HashMap<>(); 

	private final PriorityQueue<Tweet> queue = new PriorityQueue<>(10);

	/** Initialize your data structure here. */
    public Twitter() {
        
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        LinkedList<Tweet> feed = feeds.get(userId);
        if (feed == null) { 
            feed = new LinkedList<>();
            feed.add(new Tweet(tweetId));
            feeds.put(userId, feed);
            follow(userId, userId);
        } else {
            feed.add(new Tweet(tweetId));
            if (feed.size() > 10) {
                feed.removeFirst();
            }
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> follow = relations.get(userId);
        if (follow != null) {
            queue.clear();
            for (Integer f : follow) {
                LinkedList<Tweet> feed = feeds.get(f);
                if (feed != null) {
                    queue.addAll(feed);
                }
            }
            if (queue.size() > 0) {
                List<Integer> res = new ArrayList<>(Math.min(10, queue.size()));
                int i = 0;
                while(!queue.isEmpty() && i < 10) {
                    res.add(queue.poll().id);
                    i++;
                }
                return res;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }
    
    public void follow(int followerId, int followeeId) {
        Set<Integer> followers = relations.get(followerId);
        if (followers == null) { 
            followers = new HashSet<>();
            relations.put(followerId, followers);
        }
        followers.add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followerId != followeeId) {
            Set<Integer> followers = relations.get(followerId);
            if (followers != null) { 
                followers.remove((Integer) followeeId);
            }
        }
    }
    private static class Tweet implements Comparable<Tweet> {
        
        private static int TIME_INDEX = 0;

        private final int id;
        private final int time;
        
        public Tweet(int id) {
           this.id = id;
           this.time = TIME_INDEX++;
        }
        
        public int compareTo(Tweet other) {
            return other.time - time;
        }

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