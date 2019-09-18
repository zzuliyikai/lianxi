package com.coordinator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.rxjava2.test.R;

import java.io.File;

public class CollapsingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);
        getSupportActionBar().hide();

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //设置状态栏颜色
        StatusBarUtil.setStatusBarColor(this,0x55ff0000);

        File file1 = new File(Environment.getExternalStorageDirectory(), "/oohlink/");
        File file = new File(file1, "wwww.jpg");
        ImageView imageView = findViewById(R.id.iv_icon);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
       // imageView.setBackgroundDrawable(bitmapDrawable);
        imageView.setBackground(bitmapDrawable);


    }


}
