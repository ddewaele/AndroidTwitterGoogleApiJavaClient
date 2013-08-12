package com.ecs.sample;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;
import twitter4j.ResponseList;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ecs.sample.store.SharedPreferencesCredentialStore;

public class AndroidTwitterGoogleApiJavaClientActivity extends Activity {

	private SharedPreferences prefs;
	private TextView textView; 
	private HomeTimelineAdapter adapter;
	private ListView listview; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listview = (ListView) findViewById(R.id.listview);
		
		this.prefs = PreferenceManager.getDefaultSharedPreferences(this);

		Button launchOauth = (Button) findViewById(R.id.btn_launch_oauth);
		Button clearCredentials = (Button) findViewById(R.id.btn_clear_credentials);

		textView = (TextView) findViewById(R.id.response_code);
		
		launchOauth.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent().setClass(v.getContext(),OAuthAccessTokenActivity.class));
			}
		});

		clearCredentials.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				clearCredentials();
				performApiCall();
			}

		});
		
		performApiCall();

	}
	
    private void clearCredentials() {
		new SharedPreferencesCredentialStore(prefs).clearCredentials();
	}

    /**
     * Performs an authorized API call.
     */
	private void performApiCall() {
		new ApiCallExecutor().execute();
	}
	
	private class ApiCallExecutor extends AsyncTask<Uri, Void, Void> {

		@Override
		protected Void doInBackground(Uri...params) {
			
			try {
//				String tweet = "Tweet sent at " + new Date();
//				TwitterUtils.sendTweet(prefs, tweet);
				ResponseList<twitter4j.Status> homeTimeline = TwitterUtils.getHomeTimeline(prefs);
				List<twitter4j.Status> statusList = new ArrayList<twitter4j.Status>();
				for (twitter4j.Status status : homeTimeline) {
					statusList.add(status);
				}
				adapter = new HomeTimelineAdapter(AndroidTwitterGoogleApiJavaClientActivity.this,R.layout.page_timeline_row,statusList);
				
			} catch (Exception ex) {
				adapter = new HomeTimelineAdapter(AndroidTwitterGoogleApiJavaClientActivity.this,R.layout.page_timeline_row,new ArrayList<twitter4j.Status>());
				ex.printStackTrace();
			}
			
            return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}

	}


}
