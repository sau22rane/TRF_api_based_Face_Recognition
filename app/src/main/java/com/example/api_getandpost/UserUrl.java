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
import android.widget.EditText;
import android.widget.Toast;

import static com.example.api_getandpost.Global.userUrl;

public class UserUrl extends AppCompatActivity {

    Button proceed;
    EditText userIP, userPort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_url);

        proceed = (Button) findViewById(R.id.proceed);
        userIP = (EditText)findViewById(R.id.userIp);
        userPort = (EditText) findViewById(R.id.userPort);
        proceed.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                proceed.setBackgroundColor(getColor(R.color.clicked));
                userUrl = "http://"+userIP.getText()+":"+userPort.getText()+"/";
                Toast.makeText(getApplicationContext(),userUrl,Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), ClickImage.class);
                startActivity(i);
                finish();
            }
        });
    }
}
