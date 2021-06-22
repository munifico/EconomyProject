package org.techtown.economyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class OneeiActivity extends AppCompatActivity {

    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneei);

       final Intent intent = new Intent(getApplicationContext(),Showoneei.class);

       scrollView = findViewById(R.id.scrollView);
       scrollView.setHorizontalScrollBarEnabled(true);

        Button first = findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //18
                String url = "http://ecos.bok.or.kr/api/KeyStatisticList/8YLLJIA5R5RPARXVAFW1/json/kr/1/18/";
                String title = "국민소득 , 경기 , 기업경영";
                intent.putExtra("key" , url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

        Button second = findViewById(R.id.second);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //산업활동 ,소비 ,투자 범위의 데이터 보여주기
                String url = "http://ecos.bok.or.kr/api/KeyStatisticList/8YLLJIA5R5RPARXVAFW1/json/kr/19/35/";
                String title = "산업활동 , 소비 , 투자";
                intent.putExtra("key" , url);
                intent.putExtra("title",title);
                startActivity(intent);
                //intent에는 Showoneie.class가 담겨있어서 Showoneei 화면 열림
            }
        });

        Button third = findViewById(R.id.third);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //8
                String url = "http://ecos.bok.or.kr/api/KeyStatisticList/8YLLJIA5R5RPARXVAFW1/json/kr/36/43/";
                String title = "고용 , 임금 , 가계";
                intent.putExtra("key" , url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

        Button four = findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //8
                String url = "http://ecos.bok.or.kr/api/KeyStatisticList/8YLLJIA5R5RPARXVAFW1/json/kr/44/51/";
                String title = "통화, 금융";
                intent.putExtra("key" , url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

        Button five = findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //12
                String url = "http://ecos.bok.or.kr/api/KeyStatisticList/8YLLJIA5R5RPARXVAFW1/json/kr/52/63/";
                String title = "금리";
                intent.putExtra("key" , url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

        Button six = findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //6
                String url = "http://ecos.bok.or.kr/api/KeyStatisticList/8YLLJIA5R5RPARXVAFW1/json/kr/64/69/";
                String title = "증권";
                intent.putExtra("key" , url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

        Button seven = findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //10
                String url = "http://ecos.bok.or.kr/api/KeyStatisticList/8YLLJIA5R5RPARXVAFW1/json/kr/70/79/";
                String title = "물가";
                intent.putExtra("key" , url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

        Button eight = findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //8
                String url = "http://ecos.bok.or.kr/api/KeyStatisticList/8YLLJIA5R5RPARXVAFW1/json/kr/80/87/";
                String title = "국제수지, 대외거래";
                intent.putExtra("key" , url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

        Button nine = findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //7
                String url = "http://ecos.bok.or.kr/api/KeyStatisticList/8YLLJIA5R5RPARXVAFW1/json/kr/88/94/";
                String title = "환율, 외환보유";
                intent.putExtra("key" , url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

        Button ten = findViewById(R.id.ten);
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //6
                String url = "http://ecos.bok.or.kr/api/KeyStatisticList/8YLLJIA5R5RPARXVAFW1/json/kr/95/100/";
                String title = "경제관련 사회통계";
                intent.putExtra("key" , url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });


        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}