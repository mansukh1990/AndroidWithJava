package com.example.androidwithjava.utils;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.List;

public class NotificationHelper {

    public static final String CHANNEL_ID = "message_channel";
    private static int notificationId = 100;

    public static void createChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "New Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    public static boolean hasPermission(Context context) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                        == PackageManager.PERMISSION_GRANTED;
    }

    private static NotificationCompat.Builder showNotification(
            Context context,
            String title,
            String message,
            int iconResId,
            Intent destinationIntent,
            boolean useTaskStack) {

        Bitmap largeIcon = drawableToBitmap(context, iconResId);
        PendingIntent pendingIntent = buildPendingIntent(context, destinationIntent, useTaskStack);

        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(iconResId)
                .setLargeIcon(largeIcon)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOngoing(true);

    }

    private static void sendNotification(Context context, NotificationCompat.Builder builder) {
        if (!hasPermission(context)) return;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        NotificationManagerCompat.from(context).notify(getNextNotificationId(), builder.build());
    }

    public static void showNotificationSimple(
            Context context,
            String title,
            String message,
            int iconResId,
            Intent destinationIntent,
            boolean useTaskStack
    ) {
        NotificationCompat.Builder builder = showNotification(
                context, title, message, iconResId, destinationIntent, useTaskStack)
                .setOngoing(true);
        sendNotification(context, builder);

    }

    public static void showNotificationBigText(
            Context context,
            String title,
            String message,
            String bigText,
            int iconResId,
            Intent destinationIntent,
            boolean useTaskStack
    ) {
        NotificationCompat.Builder builder = showNotification(
                context, title, message, iconResId, destinationIntent, useTaskStack);
        builder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText(bigText)
                .setBigContentTitle(title)
                .setSummaryText(message));

        sendNotification(context, builder);

    }

    public static void showNotificationBigPicture(
            Context context,
            String title,
            String message,
            int bigPicture,
            int iconResId,
            Intent destinationIntent,
            boolean useTaskStack
    ) {

        Bitmap bigPictureIcon = drawableToBitmap(context, bigPicture);

        NotificationCompat.Builder builder = showNotification(
                context, title, message, iconResId, destinationIntent, useTaskStack);

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                .bigPicture(bigPictureIcon)
                .setBigContentTitle(title)
                .setSummaryText(message)
                .bigLargeIcon((Bitmap) null);

        builder.setStyle(bigPictureStyle);

        sendNotification(context, builder);


    }

    public static void showInbox(
            Context context,
            String title,
            String summary,
            List<String> lines,
            int iconResId,
            Intent destinationIntent,
            boolean useTaskStack
    ) {
        NotificationCompat.Builder builder =
                showNotification(context, title, summary, iconResId, destinationIntent, useTaskStack);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                .setBigContentTitle(title)
                .setSummaryText(summary);

        for (String line : lines) {
            inboxStyle.addLine(line);
        }

        builder.setStyle(inboxStyle);
        sendNotification(context, builder);
    }

    private static PendingIntent buildPendingIntent(
            Context context,
            Intent destinationIntent,
            boolean useRaskStack
    ) {
        int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;

        if (useRaskStack) {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntentWithParentStack(destinationIntent);
            PendingIntent pendingIntent = stackBuilder.getPendingIntent(notificationId, flags);
            if (pendingIntent != null) {
                return pendingIntent;
            }
        }
        destinationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return PendingIntent.getActivity(context, notificationId, destinationIntent, flags);
    }

    public static Bitmap drawableToBitmap(Context context, int drawableResId) {
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), drawableResId, null);
        if (drawable == null) return null;

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth() > 0 ? drawable.getIntrinsicWidth() : 1;
        int height = drawable.getIntrinsicHeight() > 0 ? drawable.getIntrinsicHeight() : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private static int getNextNotificationId() {
        return notificationId++;
    }
}