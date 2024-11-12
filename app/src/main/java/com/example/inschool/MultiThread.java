package com.example.inschool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MultiThread extends AppCompatActivity {
ProgressBar bar;
ImageView imv;
Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_thread);
        bar=findViewById(R.id.progressBar);
        imv=findViewById(R.id.imageView);
        String url="https://d1hjkbq40fs2x4.cloudfront.net/2017-08-21/files/landscape-photography_1645-t.jpg";
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadImg(url);
            }
        });
    }

    private void downloadImg(String s) {
        bar.setVisibility(View.VISIBLE);
        Thread t = new Thread(new Runnable() {
            Bitmap bitmap;

            @Override
            public void run() {
                try {
                    URL url = new URL(s);
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    BufferedInputStream buffer = new BufferedInputStream(inputStream);
                    bitmap = BitmapFactory.decodeStream(buffer);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bar.setVisibility(View.GONE);
                        imv.setImageBitmap(bitmap);
                    }
                });
            }
        });
        t.start();
    }

}