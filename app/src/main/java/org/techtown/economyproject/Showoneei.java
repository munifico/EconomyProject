package org.techtown.economyproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Showoneei extends AppCompatActivity {

    static RequestQueue requestQueue;
    RecyclerView recyclerView;
    IndicatorAdapter adapter;
    // private ArrayList<Entry> data_val = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showoneei);

        TextView textView3 = findViewById(R.id.title);

        recyclerView = findViewById(R.id.recyclerView);


        Intent intent = getIntent();
        final String title = intent.getExtras().getString("title");
        textView3.setText(title);

        final String urlStr = intent.getExtras().getString("key");

        if(requestQueue == null) {//실행되면 바로 생성하게해줌
            requestQueue = Volley.newRequestQueue(getApplicationContext()); //onCreate 안에서 request 큐라는 객체가 하나 생성
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new IndicatorAdapter();
        recyclerView.setAdapter(adapter);
        request(urlStr);

    }

    public void request(String urlStr){

        StringRequest request = new StringRequest(
                Request.Method.GET,
                urlStr, //어떤 사이트에 들어갈건지
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {   //응답이 제대로 오면 이쪽으로 떨어짐

                        processResponse(response); //문자열 응답으로온 문자열 처리하는 함수
                        //url로 받은 데이터들을 처리하기위해 prcessResponse 메서드 실행
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"에러", Toast.LENGTH_LONG).show();
                        //  println("에러 -> " + error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { //요청 파라미터를 처리하는 메소드
                Map<String , String> params = new HashMap<String, String>();
                //요청객체 생성 완료 -> 만들어진 객체를 큐에다가 넣어야함

                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);  //이 안에서 자동으로 큐에 객체 넣어줌
        //  println("요청 보냄");

    }

    public void processResponse(String response){
        Gson gson = new Gson();
        EconomicIndicatorsList economicIndicatorsList = gson.fromJson(response , EconomicIndicatorsList.class);
        //받아온 json문자열을 gson을 이용해서 자바객체로 자동으로 변환
        for(int i =0 ;i<economicIndicatorsList.KeyStatisticList.row.size(); i++){
            //json으로 받은 데이터의 갯수만큼 실행
            EconomicIndicators indicators = economicIndicatorsList.KeyStatisticList.row.get(i);

            adapter.addItem(indicators);
            //받아온 json값을 자바객체로 변경 뒤 adapter에 넣어줌줌
        }
       adapter.notifyDataSetChanged();


    }


}