package com.interview.truemedinterview.acitivites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

import com.interview.truemedinterview.R;
import com.interview.truemedinterview.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    private Activity mActivity;
    private Animation animScreenSlideInfromBottom;
    ActivitySplashScreenBinding binding;
    Animation fadeIn = new AlphaAnimation(0, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initContext();
        initData();
    }


    private void initContext() {
        mActivity = SplashScreen.this;

        // To show fade in Animation
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(2000);
        // To show slide animation from bottom to default location.
        animScreenSlideInfromBottom = AnimationUtils.loadAnimation(mActivity, R.anim.slide_in_from_bottom_home_search);
    }

    private void initData() {
        StartAnimation();
    }

    // Start and listen to animation completion
    private void StartAnimation() {

        binding.relLogo.startAnimation(animScreenSlideInfromBottom);
        animScreenSlideInfromBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                goAhead();
            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(fadeIn);
        binding.relMain.setAnimation(animation);
    }

    // Go to next activity
    private void goAhead() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }


}