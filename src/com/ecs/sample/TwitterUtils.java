package com.ecs.sample;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.content.SharedPreferences;

import com.ecs.sample.store.SharedPreferencesCredentialStore;

public class TwitterUtils {

	public static boolean isAuthenticated(SharedPreferences prefs) {

		String[] tokens = new SharedPreferencesCredentialStore(prefs).read();
		AccessToken a = new AccessToken(tokens[0],tokens[1]);
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Constants.API_KEY, Constants.API_SECRET);
		twitter.setOAuthAccessToken(a);
		
		try {
			twitter.getAccountSettings();
			return true;
		} catch (TwitterException e) {
			return false;
		}
	}
	
	public static ResponseList<Status> getHomeTimeline(SharedPreferences prefs) throws Exception {
		String[] tokens = new SharedPreferencesCredentialStore(prefs).read();
		AccessToken a = new AccessToken(tokens[0],tokens[1]);
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Constants.API_KEY, Constants.API_SECRET);
		twitter.setOAuthAccessToken(a);
        ResponseList<Status> homeTimeline = twitter.getHomeTimeline();
        return homeTimeline;
	}
	
	public static void sendTweet(SharedPreferences prefs,String msg) throws Exception {
		String[] tokens = new SharedPreferencesCredentialStore(prefs).read();
		AccessToken a = new AccessToken(tokens[0],tokens[1]);
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Constants.API_KEY, Constants.API_SECRET);
		twitter.setOAuthAccessToken(a);
        twitter.updateStatus(msg);
	}	
}
