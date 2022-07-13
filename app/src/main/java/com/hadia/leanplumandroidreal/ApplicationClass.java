package com.hadia.leanplumandroidreal;

import android.app.Application;
import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.leanplum.Leanplum;

// For tracking user sessions.
import com.leanplum.LeanplumActivityHelper;

import com.leanplum.LeanplumApplication;
import com.leanplum.LeanplumPushService;
import com.leanplum.Var;
import com.leanplum.annotations.Parser;
import com.leanplum.LeanplumPushNotificationCustomizer;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ApplicationClass extends Application {

    //set variables
    //@Variable public static String lpGameTitle = "Fun Game";
    //public static Var<String> lpGameBgImg = Var.defineAsset("gameBgImg", "gamebgimg");
    /*@Variable public static Map<String, Object> lpPowerup = new HashMap<String, Object>() {
        {
            put("lpName", "Turbo Boost");
            put("lpPrice", 150);
            put("lpSpeed", 1.5);
            put("lpTimeout", 15);
        }
    };*/


    @Override
    public void onCreate() {
        super.onCreate();

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this,GameActivity.class); //setting application context on where you are defining variables in the classes

        //  For session lifecyle tracking. Must be called if you do not extend LeanplumApplication Class
        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        // Insert your API keys here.
        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("app_xvhLIDh3sIs71lx6yMYEW8LhhodWlFwvAUPeM4JoCSQ", "dev_nUVY7kDD4NFROS0HSBB8c1UDnRwb17tb5d1smWQI93o");
            //Leanplum.setAppIdForProductionMode("app_xvhLIDh3sIs71lx6yMYEW8LhhodWlFwvAUPeM4JoCSQ", "prod_wu6BfCX0f4MY6lRgqDytTUWPxDYZOIEbS0MqZgIFKGk");
        } else {
            Leanplum.setAppIdForProductionMode("app_IF2eLLGxH8jme6fyd4qe3AnJDPuyuV2W6mYLMORepkQ", "dev_08KT3uYYrZdwku6wgMOiZywlYETW6r8j9XetCriYdlM");
        }

        //Custom push notif
        LeanplumPushService.setCustomizer(new LeanplumPushNotificationCustomizer() {
            @Override
            public void customize(NotificationCompat.Builder builder, Bundle notificationPayload) {
                Log.i("### LP " , "notification customized");
                // Setting a custom smallIcon included in the Drawable folder
                builder.setSmallIcon(R.drawable.gamebgimg);


            }
            @Override
            public void customize(Notification.Builder builder, Bundle bundle, @Nullable Notification.Style style) {

            }
        });

        //set variables
        //Var<String> lpGameBgImg = Var.defineAsset("gameBgImg", "gamebgimg");


        // Optional: Tracks all screens in your app as states in Leanplum.
        // Leanplum.trackAllAppScreens();

        // This will only run once per session, even if the activity is restarted.
        Leanplum.start(this);
        //Log("Leanplum started");


    }

}
