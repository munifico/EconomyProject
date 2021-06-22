package org.techtown.economyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class ExplainActivity extends AppCompatActivity {

    ImageView kospi;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

       scrollView = findViewById(R.id.scrollView);
       kospi = findViewById(R.id.kospi);
       scrollView.setHorizontalScrollBarEnabled(true);




    }
}