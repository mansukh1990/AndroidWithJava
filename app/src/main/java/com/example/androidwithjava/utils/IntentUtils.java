package com.example.androidwithjava.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class IntentUtils {

    public static void dialNumber(
            Context context,
            String phoneNumber
    ) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivityIntent(context, intent, "No phone application found");

    }

    public static void sendMessage(
            Context context,
            String phoneNumber,
            String message
    ) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        intent.putExtra("sms_body", message);
        startActivityIntent(context, intent, "No message application found");
    }

    public static void sendEmail(
            Context context,
            String[] recipients,
            String subject,
            String body
    ) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        startActivityIntent(context, intent, "No email application found");
    }

    public static void shareText(Context context, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivityIntent(context, Intent.createChooser(intent, "Share via"), "No share application found");
    }

    private static void startActivityIntent(
            Context context,
            Intent intent,
            String errorMessage
    ) {

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        }

    }
}
