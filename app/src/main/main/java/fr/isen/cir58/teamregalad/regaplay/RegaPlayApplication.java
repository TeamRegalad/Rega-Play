package fr.isen.cir58.teamregalad.regaplay;

import android.app.Application;
import android.content.Context;

/**
 * Created by thomas on 22/10/15.
 */
public class RegaPlayApplication extends Application{
    private static Context sContext;

    public void onCreate(){
        super.onCreate();

        // Keep a reference to the application context
        sContext = getApplicationContext();
    }

    // Used to access Context anywhere within the app
    public static Context getContext() {
        return sContext;
    }
}
