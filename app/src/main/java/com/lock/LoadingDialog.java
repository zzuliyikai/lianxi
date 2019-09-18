package com.lock;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import com.rxjava2.test.R;

/**
 *
 *
 */
public class LoadingDialog extends AlertDialog {

    private TextView tips_loading_msg;

    private String message = null;
    public LoadingDialog(Context context, String message) {
        super(context);
        this.message = message;
        this.setCancelable(false);
    }

    public LoadingDialog(Context context, int theme, String message) {
        super(context, theme);
        this.message = message;
        this.setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_tips_loading);

        //计算dialog的宽度
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width= (int) (widthPixels*0.5);
        getWindow().setAttributes(params);

        tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
        RotateLoading rl= (RotateLoading) findViewById(R.id.rl);
        rl.start();
        tips_loading_msg.setText(this.message);
    }

    public void setText(String message) {
        this.message = message;
        tips_loading_msg.setText(this.message);
    }

    public void setText(int resId) {
        setText(getContext().getResources().getString(resId));
    }
}
