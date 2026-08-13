package com.example.androidwithjava.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.androidwithjava.R;

public class AlertDialogUtils {

    public static void showAlertDialogOneButton(
            Context context,
            String title,
            @DrawableRes int icon,
            String message,
            String positiveText,
            @Nullable DialogInterface.OnClickListener positiveListener
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);
        builder.setMessage(message);

        if (icon != 0) {
            builder.setIcon(icon);
        }

        if (positiveText != null && !positiveText.isEmpty()) {
            builder.setPositiveButton(
                    positiveText,
                    positiveListener
            ).create();

        }
        builder.setCancelable(false);
        builder.show();
    }

    public static void showAlertDialogTwoButton(
            Context context,
            String title,
            @DrawableRes int icon,
            String message,
            String positiveText,
            String negativeText,
            DialogInterface.OnClickListener positiveListener,
            DialogInterface.OnClickListener negativeListener
    ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);
        builder.setMessage(message);
        if (icon != 0) {
            builder.setIcon(icon);
        }
        if (positiveText != null && !positiveText.isEmpty()) {
            builder.setPositiveButton(
                    positiveText,
                    positiveListener
            );
        }
        if (negativeText != null && !negativeText.isEmpty()) {
            builder.setNegativeButton(
                    negativeText,
                    negativeListener
            );
        }
        builder.setCancelable(false);
        builder.show();

    }

    public static void showAlertDialogThreeButton(
            Context context,
            String title,
            @DrawableRes int icon,
            String message,
            String positiveText,
            String negativeText,
            String neutralText,
            DialogInterface.OnClickListener positiveListener,
            DialogInterface.OnClickListener negativeListener,
            DialogInterface.OnClickListener neutralListener
    ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);
        builder.setMessage(message);
        if (icon != 0) {
            builder.setIcon(icon);
        }
        if (positiveText != null && !positiveText.isEmpty()) {
            builder.setPositiveButton(
                    positiveText,
                    positiveListener
            );
        }
        if (negativeText != null && !negativeText.isEmpty()) {
            builder.setNegativeButton(
                    negativeText,
                    negativeListener
            );
        }
        if (neutralText != null && !neutralText.isEmpty()) {
            builder.setNeutralButton(
                    neutralText,
                    neutralListener
            );
        }
        builder.setCancelable(false);
        builder.show();

    }

    public static void showCustomDialog(
            Context context,
            @DrawableRes int icon,
            String title,
            String message
    ) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_alert_custom);

        Window window = dialog.getWindow();

        if (window != null) {
            window.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
            );
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        Button btnOkay = dialog.findViewById(R.id.btnOkay);
        TextView titles=dialog.findViewById(R.id.txtTitle);
        TextView messages = dialog.findViewById(R.id.txtMessage);
        ImageView imageSuccessFail = dialog.findViewById(R.id.imgSuccessFail);

        titles.setText(title);
        messages.setText(message);
        imageSuccessFail.setImageResource(icon);

        btnOkay.setOnClickListener(view -> {
            Toast.makeText(context, "Dialog Closed", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.setCancelable(false);
        dialog.show();

    }
}
