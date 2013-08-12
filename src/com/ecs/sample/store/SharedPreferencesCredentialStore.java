package com.ecs.sample.store;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesCredentialStore implements CredentialStore {

	private static final String TOKEN = "token";
	private static final String TOKEN_SECRET = "token_secret";

	private SharedPreferences prefs;
	
	public SharedPreferencesCredentialStore(SharedPreferences prefs) {
		this.prefs = prefs;
	}
	
	public String[] read() {
		String[] tokens = new String[2];
		tokens[0]=prefs.getString(TOKEN, "");
		tokens[1]=prefs.getString(TOKEN_SECRET, "");
		return tokens;
	}

	public void write(String[] tokens) {
		Editor editor = prefs.edit();
		editor.putString(TOKEN,tokens[0]);
		editor.putString(TOKEN_SECRET,tokens[1]);
		editor.commit();
	}
	
	public void clearCredentials() {
		Editor editor = prefs.edit();
		editor.remove(TOKEN);
		editor.remove(TOKEN_SECRET);
		editor.commit();
	}
}
