##Introduction

##Software requirements


##Project setup

### m2e-android plugin

This project is built using the [m2e-android plugin](http://rgladwell.github.io/m2e-android/index.html) to handle its external dependencies.

When using Eclipse ADT, it assumes that the following components are installed :

- Eclipse Market Client
- [m2e-android plugin](http://rgladwell.github.io/m2e-android/index.html)
- [maven-android-sdk-deployer](https://github.com/mosabua/)

### Eclipse MarketPlace

If you don't have the Eclipse Market Client installed, you can install it by clicking on 

```Help → Install new Software → Switch to the Juno Repository → General Purpose Tools → Marketplace Client```

![](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/AndroidMavenSetup/1_available_soft.PNG)

Once you have the Eclipse Market Client installed, you can proceed to install the m2e-android plugin

![](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/AndroidMavenSetup/2_marketplace.PNG)

```Help -> Eclipse Marketplace... and search for "android m2e".```

![](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/AndroidMavenSetup/3_android_m2e.PNG)

More instructions can be found on the [m2e-android plugin](http://rgladwell.github.io/m2e-android/index.html) site.

### maven-android-sdk-deployer

Clone the [maven-android-sdk-deployer](https://github.com/mosabua/maven-android-sdk-deployer) and execute mvn install. This will install the required projects into your local maven repository.

### Environment setup

Make sure you have your ANDROID_HOME variable pointing to your SDK

![](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/AndroidMavenSetup/4_env_variable.PNG)

### Clone and import the project

Clone the project on your filesystem

	git clone git@github.com:ddewaele/AndroidTwitterGoogleApiJavaClient.git
	Cloning into AndroidTwitterGoogleApiJavaClient...
	remote: Reusing existing pack: 145, done.
	remote: Counting objects: 11, done.
	remote: Compressing objects: 100% (8/8), done.
	remote: Total 156 (delta 0), reused 0 (delta 0)
	Receiving objects: 100% (156/156), 529.74 KiB | 483 KiB/s, done.
	Resolving deltas: 100% (33/33), done.

Import the project in Eclipse ADT.

When importing the project, select ```Existing Maven Projects```.

![](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/OAuth1Twitter/adt-import.png)

Navigate to the place where you cloned the repository (ex: /Users/ddewaele/Projects/AndroidTwitterGoogleApiJavaClient) and click Finish.

![](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/OAuth1Twitter/adt-import2.png)

After that, Eclipse ADT and the Maven M2E plugin should import your project and setup all the dependencies.

![](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/OAuth1Twitter/adt-import3.png)


### Twitter OAuth application and keys

As this application allows you to interact with Twitter, you need to go to to the [Twitter Developer page](dev.twitter.com) to [register a new application](https://apps.twitter.com/app/new).

Here are a couple of steps you need to follow

- Register a new application in the Twitter dev console

![](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/OAuth1Twitter/app-create.png)

- Verify that the application was created succesfully.

![](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/OAuth1Twitter/app-list.png)

- Set the correct permissions

![](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/OAuth1Twitter/app-permissions.png)

- Copy the key and secret from the app.

![](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/OAuth1Twitter/app-keys.png)

- Put the key and secret in the [Constants file](https://github.com/ddewaele/AndroidTwitterGoogleApiJavaClient/blob/master/src/com/ecs/sample/Constants.java)

Replace the placeholders below with the actual KEY and SECRET

	public static final String API_KEY = "PUT YOUR TWITTER API KEY HERE";
	public static final String API_SECRET= "PUT YOUR TWITTER API SECRET HERE";


Launch the application and you should see the following screens.

![Twitter login](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/OAuth1Twitter/2-twitter-login.png)
![Twitter authorization](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/OAuth1Twitter/3-twitter-authorize.png)
![Twitter tweets](https://dl.dropboxusercontent.com/u/13246619/Blog%20Articles/OAuth1Twitter/4-twitter-stream.png)
