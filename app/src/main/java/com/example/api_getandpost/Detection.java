package com.example.api_getandpost;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import static com.example.api_getandpost.Global.imageEncoded;
import static com.example.api_getandpost.Global.userUrl;

public class Detection extends AppCompatActivity implements url.urlExecution {
    ImageView detected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image);
        detected = (ImageView) findViewById(R.id.detected);

        url mUrl = new url();
        mUrl.execution = this;
        Toast.makeText(getApplicationContext(),"Sending Frame for Detection",Toast.LENGTH_LONG).show();
        mUrl.execute(userUrl+"api/detect",imageEncoded);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void processFinish(String output) {
        Toast.makeText(getApplicationContext(),"Frame recieved",Toast.LENGTH_LONG).show();
        Picasso.get().load(userUrl+"api/download").into(detected);
    }
}
