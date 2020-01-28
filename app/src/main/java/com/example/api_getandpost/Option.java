package com.example.api_getandpost;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import static com.example.api_getandpost.Global.mBitmap;

public class Option extends AppCompatActivity {
    Button upload,detect;
    ImageView userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_option);
        upload = (Button) findViewById(R.id.uploadScreen);
        detect = (Button) findViewById(R.id.detectScreen);
        userData = (ImageView) findViewById(R.id.selectorDisplay);
        userData.setMaxHeight(mBitmap.getHeight());
        userData.setMaxWidth(mBitmap.getWidth());
        userData.setImageBitmap(mBitmap);
        detect.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                detect.setBackgroundColor(getColor(R.color.clicked));
                Intent i =new Intent(getApplicationContext(), Detection.class);
                startActivity(i);
                finish();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                upload.setBackgroundColor(getColor(R.color.clicked));
                Intent i =new Intent(getApplicationContext(),uploadData.class);
                startActivity(i);
                finish();
            }
        });
    }
}
