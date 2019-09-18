package com.rxjava2.test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GlideActivity extends AppCompatActivity {

    private TextView tv_object_animator;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        tv_object_animator = findViewById(R.id.tv_object_animator);

        Glide.with(this).load("").into(imageView);
    }

    public void loadImage(View view) {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(tv_object_animator, "translationX", -500, 0);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(tv_object_animator, "rotation", 0, 360);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(tv_object_animator, "alpha", 1, 0, 1);

        AnimatorSet animationSet = new AnimatorSet();

        animationSet.play(rotation).after(translationX).with(alpha);

        animationSet.setDuration(5000);

        animationSet.start();


    }
}
