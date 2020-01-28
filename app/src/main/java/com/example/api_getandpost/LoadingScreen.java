package com.example.api_getandpost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import static com.example.api_getandpost.Global.imageEncoded;
import static com.example.api_getandpost.Global.mBitmap;

public class LoadingScreen extends AppCompatActivity {
    ImageView loadingImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading_screen);
        loadingImage = (ImageView)findViewById(R.id.loadingInmage);
        loadingImage.setImageBitmap(mBitmap);
        Thread encoding = new Thread(){
            public void run(){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
                Intent i = new Intent(getApplicationContext(),Option.class);
                startActivity(i);
                finish();
            }
        };
        encoding.start();
    }
}
