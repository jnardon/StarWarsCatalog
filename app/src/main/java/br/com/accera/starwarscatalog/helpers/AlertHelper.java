package br.com.accera.starwarscatalog.helpers;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by juliano.nardon on 23/01/18.
 */

public class AlertHelper {

    public static final AlertHelper instance = new AlertHelper();

    public ProgressDialog loader(Context context, String message) {
        final ProgressDialog dialog =
                new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        return dialog;
    }
}
