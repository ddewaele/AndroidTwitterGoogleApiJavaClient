package com.ecs.sample;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ecs.sample.store.SharedPreferencesCredentialStore;

public class AndroidTwitterGoogleApiJavaClientActivity extends Activity {

	private SharedPreferences prefs;
	private TextView textView; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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
		try {
			String tweet = "Tweet sent at " + new Date();
			TwitterUtils.sendTweet(prefs, tweet);
			textView.setText(tweet);
		} catch (Exception ex) {
			ex.printStackTrace();
			textView.setText("Error occured : " + ex.getMessage());
		}
	}

}
