package com.codepath.apps.restclienttemplate.network;

import android.content.Context;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.oauth.OAuthBaseClient;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.api.BaseApi;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/scribejava/scribejava/tree/master/scribejava-apis/src/main/java/com/github/scribejava/apis
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
	public static final BaseApi REST_API_INSTANCE = TwitterApi.instance(); // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
//	public static final String REST_CONSUMER_KEY = "XXXX";       // Change this
//	public static final String REST_CONSUMER_SECRET = "XXXXXX"; // Change this

	// Landing page to indicate the OAuth flow worked in case Chrome for Android 25+ blocks navigation back to the app.
	public static final String FALLBACK_URL = "https://codepath.github.io/android-rest-client-template/success.html";

	// See https://developer.chrome.com/multidevice/android/intents
	public static final String REST_CALLBACK_URL_TEMPLATE = "intent://%s#Intent;action=android.intent.action.VIEW;scheme=%s;package=%s;S.browser_fallback_url=%s;end";

	public TwitterClient(Context context) {
		super(context, REST_API_INSTANCE,
				REST_URL,
				context.getString(R.string.REST_CONSUMER_KEY),
				context.getString(R.string.REST_CONSUMER_SECRET),
				String.format(REST_CALLBACK_URL_TEMPLATE, context.getString(R.string.intent_host),
						context.getString(R.string.intent_scheme), context.getPackageName(), FALLBACK_URL));
	}
	// CHANGE THIS
	// DEFINE METHODS for different API endpoints here
	public void getHomeTimeline(long max_id, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/home_timeline.json");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		params.put("count",20);
		//params.put("since_id",1);
		if(max_id > -1) {
			params.put("max_id", max_id);
		}
		client.get(apiUrl, params, handler);
	}

	// DEFINE METHODS for different API endpoints here
	public void getMentionsTimeline(long max_id, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		params.put("count",20);
		params.put("since_id",1);
		client.get(apiUrl, params, handler);
	}

	// get user timeline
	public void getUserTimeline(String screename, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/user_timeline.json");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		params.put("count",20);
		params.put("screen_name",screename);
		client.get(apiUrl, params, handler);
	}

	//verify credentials and get user info
	public  void getUserInfo(AsyncHttpResponseHandler handler){
		String apiUrl = getApiUrl("account/verify_credentials.json");
		client.get(apiUrl,null,handler);
	}

	public void postNewTweet(String tweetStatus,long inReplyToID, AsyncHttpResponseHandler handler){
		String apiURL = getApiUrl("statuses/update.json");
		RequestParams params = new RequestParams();
		params.put("status",tweetStatus);
		if(inReplyToID > -1) {
			params.put("in_reply_to_status_id", inReplyToID);
		}
		client.post(apiURL,params,handler);
	}

	public void postFavorite(long tweetId, boolean favorited, JsonHttpResponseHandler handler) {
		String endpoint = favorited ? "create" : "destroy";
		String apiUrl = getApiUrl("favorites/" + endpoint + ".json");
		RequestParams params = new RequestParams();
		params.put("id", tweetId);
		client.post(apiUrl, params, handler);
	}

	public void postRetweet(long tweetId, boolean reTweeted, JsonHttpResponseHandler handler) {
		String endpoint = reTweeted ? "retweet" : "unretweet";
		String apiUrl = getApiUrl("statuses/" + endpoint + "/" + tweetId + ".json");
		RequestParams params = new RequestParams();
		params.put("id", tweetId);
		client.post(apiUrl, params, handler);
	}



	/* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
	 * 	  i.e getApiUrl("statuses/home_timeline.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */
}
