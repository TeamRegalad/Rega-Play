package fr.isen.cir58.teamregalad.regaplay.social;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Paul on 10/26/2015.
 */
public class shareMusicInfo {
    public static void sendSMS(Activity activity, String musicInfo)
    {
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("sms:"));
        smsIntent.putExtra("sms_body", musicInfo);

        if(smsIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(smsIntent);
        }
    }

    public static void sendMail(Activity activity, String musicInfo)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Sharing Music");
        intent.putExtra(Intent.EXTRA_TEXT, musicInfo);

        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        }

    }
}
