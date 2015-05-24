package com.cmip.cmip2015.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cmip.cmip2015.R;
import com.cmip.cmip2015.fragments.ConnectDialogFragment;
import com.cmip.cmip2015.fragments.LoginFragment;
import com.cmip.cmip2015.fragments.RegisterFragment;
import com.cmip.cmip2015.logs.Logger;
import com.cmip.cmip2015.pojo.User;


public class SplashActivity extends AppCompatActivity
        implements View.OnClickListener , RegisterFragment.OnRegistrationListener,
        LoginFragment.OnLoginListener{

    private LinearLayout layoutContainer;
    private ImageView splashIcon;
    //    private LoginButton btnFacebook;
    private ConnectDialogFragment dialog;
    public Context context = this;

    private User user;

    private int response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        user = new User();

        layoutContainer = (LinearLayout) findViewById(R.id.buttons_container);
        splashIcon = (ImageView) findViewById(R.id.splash_icon);

        // Social Buttons
//        btnFacebook = (LoginButton) findViewById(R.id.btnFacebookConnect);

        findViewById(R.id.btnGoogleConnect).setOnClickListener(this);
//        findViewById(R.id.btnTwitterConnect).setOnClickListener(this);
        findViewById(R.id.btnEmailConnect).setOnClickListener(this);
        findViewById(R.id.btnSkipConnect).setOnClickListener(this);


//        btnFacebook.setOnClickListener(this);
//        delay(10000); // holds logo on screen for 3s

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                splashIcon.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        splashIcon.startAnimation(slideUp);
        layoutContainer.startAnimation(fadeIn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnEmailConnect:
                dialog = new ConnectDialogFragment();
                dialog.show(getSupportFragmentManager(), ConnectDialogFragment.TAG);
                break;
            case R.id.btnSkipConnect:
                Logger.toastShort(SplashActivity.this, "Clicked skip");
                startActivity(new Intent(SplashActivity.this, UserApplianceActivity.class));
                break;
            case R.id.btnGoogleConnect:
                Logger.toastShort(SplashActivity.this, "Clicked Google button");
                break;
        }
    }

    @Override
    public int attemptLogin(String username, String password) {
        return 0;
    }

    @Override
    public void cancelLogin() {
        dialog.dismiss();
    }

    @Override
    public int attemptRegistration(String username, String email, String password) {
        return 0;
    }

    @Override
    public void cancelRegistration() {
        dialog.dismiss();
    }
}
