package com.ecs.sample.store;


public interface CredentialStore {

  String[] read();
  void write(String[]response);
  void clearCredentials();
}
