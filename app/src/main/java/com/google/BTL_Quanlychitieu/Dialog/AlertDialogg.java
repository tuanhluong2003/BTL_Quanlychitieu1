package com.google.BTL_Quanlychitieu.Dialog;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.google.BTL_Quanlychitieu.Listener.DialogListener;
import com.google.BTL_Quanlychitieu.R;

public class AlertDialogg {

    String title;
    String message;
    int icon;

    Context context;

    DialogListener dialogListener;
    public AlertDialogg(Context context, String title, String message, int icon)
    {
            this.icon = icon;
            this.message = message;
            this.title =  title;
            this.context = context;
    }

    public AlertDialogg setDialogListener(DialogListener listener)
    {
        this.dialogListener = listener;
        return this;
    }

    public void show()
    {
        AlertDialog.Builder mydialog = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        mydialog.setTitle(title);
        mydialog.setMessage(message);
        mydialog.setIcon(icon);
        mydialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.dialogPositive();
            }
        });

        mydialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mydialog.show();
    }
}
