package com.example.api_getandpost;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static com.example.api_getandpost.Global.imageEncoded;
import static com.example.api_getandpost.Global.mBitmap;
import static com.example.api_getandpost.Global.userUrl;

public class uploadData extends AppCompatActivity implements url.urlExecution {

    ImageView userData;
    Button upload;
    EditText name;
    url apiUrl = new url();
    url apiImageUrl = new url();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upload_data);

        apiUrl.execution=this;
        apiImageUrl.execution=this;
        userData = (ImageView) findViewById(R.id.userData);
        userData.setImageBitmap(mBitmap);
        upload = (Button) findViewById(R.id.upload);
        name = (EditText) findViewById(R.id.name);
        upload.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String dataName = String.valueOf(name.getText());
                upload.setBackgroundColor(getColor(R.color.clicked));
                apiUrl.execute(userUrl+"api/dataset/name",dataName);
                apiImageUrl.execute(userUrl+"api/dataset/image",imageEncoded);
                finish();
            }
        });
    }

    @Override
    public void processFinish(String output) {

    }
}
