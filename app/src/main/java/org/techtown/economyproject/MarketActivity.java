package org.techtown.economyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//데이터 가져오는 것까지 진행
public class MarketActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;

    static RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            //requestQueue 객체 생성하기 -> 나중에는 이거 맨 위에 코드로 써야함
        }

        textView = findViewById(R.id.textData);
        String url = "https://economyapp.run.goorm.io/ans";
        makeRequest(url);

    }
    public void makeRequest(String url){

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.append(error.getMessage());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String ,String>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }



    public void println(String data){
        float value = Float.parseFloat(data);
        if( value >= 2.5){
            textView.append("점수가 2.5이상이면 매수하는게 좋은데 우리가 나온 점수는 " + data+ " 이므로 매수하는 것이 좋다" + "\n");
        } else{
            textView.append("점수가 2.5이상이면 매수하는게 좋은데 우리가 나온 점수는 " + data+ " 이므로 매수하지 않는 것이 좋다" + "\n");
        }

    }

    public void processResponse(String response){
        Gson gson = new Gson();
        ResultValue rv =gson.fromJson(response, ResultValue.class);
        //가져온 Json객체를 자바객체로 변경
        float value = Float.parseFloat(rv.Value);
        //소숫점까지 나타내기 위해서 float로 파싱
        println(String.format("%.2f",value));



    }
}