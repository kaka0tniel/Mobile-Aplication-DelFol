package com.otniel.delfol.delfol;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000);//delay 3 detik
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        image.startAnimation(animation1);
    }
}
