package org.techtown.economyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button oneei = findViewById(R.id.oneei);
        Button uei = findViewById(R.id.uei);
        Button kospi = findViewById(R.id.kospi);
        Button markettiming = findViewById(R.id.markettiming);
        Button explain = findViewById(R.id.explain);

        oneei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext() , OneeiActivity.class);
                startActivity(intent);
            }
        });

        uei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext() , UeiActivity.class);
                startActivity(intent);
            }
        });

        kospi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://finance.naver.com/sise/sise_index.nhn?code=KPI200"));
                startActivity(intent);
            }
        });

        markettiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , MarketActivity.class);
                startActivity(intent);
            }
        });

        explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , ExplainActivity.class);
                startActivity(intent);
            }
        });

    }
}