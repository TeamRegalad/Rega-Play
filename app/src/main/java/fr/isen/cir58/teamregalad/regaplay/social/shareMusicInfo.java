package fr.isen.cir58.teamregalad.regaplay.social;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by Paul on 10/26/2015.
 */
public class shareMusicInfo {
    public static void sendSMS(Activity activity, String musicInfo) {
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("sms:"));
        smsIntent.putExtra("sms_body", musicInfo);

        if (smsIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(smsIntent);
        }
    }

    public static void sendMail(Activity activity, String musicInfo) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Sharing Music");
        intent.putExtra(Intent.EXTRA_TEXT, musicInfo);

        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        }

    }

    public static void sendTweet(Activity activity, String musicInfo)
    {
        final PackageManager packageManager = activity.getPackageManager();
        final Context context = activity.getApplicationContext();

        try
        {
            // Check if the Twitter app is installed on the phone.
            packageManager.getPackageInfo("com.twitter.android", 0);

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setClassName("com.twitter.android", "com.twitter.android.composer.ComposerActivity");
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, musicInfo);
            activity.startActivity(intent);

        }
        catch (Exception e) // If the app is not installed, open twitter with the browser
        {
            Toast.makeText(context, "Twitter is not installed on this device", Toast.LENGTH_SHORT).show();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("https://twitter.com/intent/tweet?text=%s", musicInfo)));
            activity.startActivity(browserIntent);
        }
    }
}
