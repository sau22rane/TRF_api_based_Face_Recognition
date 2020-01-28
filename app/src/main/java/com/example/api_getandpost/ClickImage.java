package com.example.api_getandpost;

import androidx.appcompat.app.AppCompatActivity;

import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import static com.example.api_getandpost.Global.mBitmap;

public class ClickImage extends AppCompatActivity {
    CameraView mCameraView;
    Button click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        click = (Button)findViewById(R.id.click);

        mCameraView = (CameraView)findViewById(R.id.pic);
        mCameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                mBitmap=cameraKitImage.getBitmap();

                Intent i = new Intent(getApplicationContext(),LoadingScreen.class);
                mCameraView.stop();
                startActivity(i);
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraView.captureImage();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.start();
    }
}
