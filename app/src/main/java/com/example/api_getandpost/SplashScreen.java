package com.example.api_getandpost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView Im1,Im2;
    Animation fade_in,scale_big;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        Im1=findViewById(R.id.imageView2);
        Im2=findViewById(R.id.im2);
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    fade_in= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.fade_in);
                    scale_big=AnimationUtils.loadAnimation(SplashScreen.this,R.anim.scale_big);
                    // After 5 seconds redirect to another intent
                    //Remove activity
                    Im1.startAnimation(fade_in);
                    Im2.startAnimation(scale_big);
                    sleep(3*1000);
                }
                catch (Exception e) {

                }
                finally {
                    Im2.setVisibility(View.INVISIBLE);
                    Im1.setVisibility(View.INVISIBLE);
                    //Intent i=new Intent(getBaseContext(),Home.class);
                    Intent i=new Intent(getBaseContext(),UserUrl.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_down,R.anim.fade_out);
                    finish();
                }
            }
        };
        // start thread
        background.start();

    }
}
