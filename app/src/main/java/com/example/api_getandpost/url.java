package com.example.api_getandpost;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class url extends AsyncTask<String, Void, String> {


    public urlExecution execution = null;

    public interface urlExecution {
        void processFinish(String output);
    }

    @Override
    protected void onPostExecute(String result) {
        execution.processFinish(result);
    }

    @Override
    protected String doInBackground(String... requestURL) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL[0]);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "Detection/jpeg");


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(requestURL[1]);

            conn.connect();
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                Bitmap bitmap = null;
                InputStream input = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
                Log.d("RaneTag", String.valueOf(bitmap));
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("RaneTag",response);
        return null;
    }
}
