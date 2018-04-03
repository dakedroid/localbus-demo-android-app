package com.example.dake10.localbus;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

/**
 * Created by dake10 on 1/19/16.
 */
public class AppiSync extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    /*
     * In this tutorial, we'll subclass ParseObjects for convenience to
     * create and modify Photo objects.
     *
     * Also, we'll use an Activity class to keep track of the relationships
     * of ParseUsers with each other and Photos. Every time a user follows, likes
     * or comments, a new activity is created to represent the relationship.
     */
        ParseObject.registerSubclass(GeoBus.class);
    /*
     * Fill in this section with your Parse credentials
     */

        // Set your Facebook App Id in strings.xml
       // ParseFacebookUtils.initialize("373432736168885");


    /*
     * For more information on app security and Parse ACL:
     * https://www.parse.com/docs/android_guide#security-recommendations
     */
        ParseACL defaultACL = new ParseACL();

    /*
     * If you would like all objects to be private by default, remove this
     * line
     */
        defaultACL.setPublicReadAccess(true);

    /*
     * Default ACL is public read access, and user read/write access
     */
        ParseACL.setDefaultACL(defaultACL, true);

    /*
     *  Register for push notifications.
     */

    }

}
