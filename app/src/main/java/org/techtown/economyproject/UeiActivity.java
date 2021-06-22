package org.techtown.economyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class UeiActivity extends AppCompatActivity {

    static RequestQueue requestQueue;
    static String[] lables = new String[12];
    static String name;
    static int posi;

    //TextView testtextview;
    Spinner spinner ;
    LineChart lineChart;
    private ArrayList<Entry> data_val = new ArrayList<>();  //데이터들을 넣을 장소
    static ArrayList<Float> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uei);


        spinner = findViewById(R.id.spinner);
        lineChart = (LineChart)findViewById(R.id.linechart);

        if(requestQueue == null) {//실행되면 바로 생성하게해줌
            requestQueue = Volley.newRequestQueue(getApplicationContext()); //onCreate 안에서 request 큐라는 객체가 하나 생성
        }
        String jurl = "http://ecos.bok.or.kr/api/StatisticSearch/8YLLJIA5R5RPARXVAFW1/json/kr/1/12/080Y101/MM/202003/202104/I11AC/5";
        request(jurl);

        //클릭했을때의 처리
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String urlStr = "http://ecos.bok.or.kr/api/StatisticSearch/8YLLJIA5R5RPARXVAFW1/json/kr/1/10/010Y002/MM/201101/201101/AAAA11" ; // 여기에서 api 주소
                if(position == 0){  //경기선행지수변동치
                    urlStr = "http://ecos.bok.or.kr/api/StatisticSearch/8YLLJIA5R5RPARXVAFW1/json/kr/1/12/085Y026/MM/202003/202103/I16E";
                    name = "경기선행지수 변동치";
                    posi = position;
                    data_val.clear();
                    request(urlStr,name);
                } else if(position == 1){ //경상수지
                    urlStr = "http://ecos.bok.or.kr/api/StatisticSearch/8YLLJIA5R5RPARXVAFW1/json/kr/1/12/022Y013/MM/202003/202104/000000";
                    data_val.clear();
                    name = "경상수지";
                    posi = position;
                    request(urlStr,name);
                } else if(position == 2){   //기준금리
                    urlStr = "http://ecos.bok.or.kr/api/StatisticSearch/8YLLJIA5R5RPARXVAFW1/json/kr/1/12/098Y001/MM/202003/202104/0101000";
                    data_val.clear();
                    name = "기준금리";
                    posi = position;
                    request(urlStr,name);
                } else if(position == 3){   //,소비자 물가지수 x
                    urlStr = "http://ecos.bok.or.kr/api/StatisticSearch/8YLLJIA5R5RPARXVAFW1/json/kr/1/12/021Y125/MM/202003/202104/0";
                    data_val.clear();
                    name = "소비자 물가지수";
                    posi = position;
                    request(urlStr ,name);
                } else if(position == 4){   // 제조업 순환지수
                    //얘는 마이너스 이용해야함 ->어떻게 처리하지 ?
                    urlStr = "http://ecos.bok.or.kr/api/StatisticSearch/8YLLJIA5R5RPARXVAFW1/json/kr/1/12/080Y101/MM/202003/202104/I11AC/3";
                    data_val.clear();
                    name = "제조업 순환지수";
                    posi = position;
                    request(urlStr,name);
                }else if(position ==5){ // , 원/달러환율
                    urlStr = "http://ecos.bok.or.kr/api/StatisticSearch/8YLLJIA5R5RPARXVAFW1/json/kr/1/12/036Y004/MM/202003/202104/0000001/0000100";
                    data_val.clear();
                    name = "원/달러 환율";
                    posi = position;
                    request(urlStr,name);
                }
                //이것들 차트로 나오게하면됨


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void request(String urlStr){     //재고지수 계산만 할 request

        StringRequest request = new StringRequest(
                Request.Method.GET,
                urlStr, //어떤 사이트에 들어갈건지
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {   //응답이 제대로 오면 이쪽으로 떨어짐

                        Toast.makeText(getApplicationContext(),"응답", Toast.LENGTH_LONG).show();

                        processResponse2(response); //문자열 응답으로온 문자열 처리하는 함수
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

    public void request(String urlStr , final String name){

        StringRequest request = new StringRequest(
                Request.Method.GET,
                urlStr, //어떤 사이트에 들어갈건지
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {   //응답이 제대로 오면 이쪽으로 떨어짐

                       // testtextview.append(response);
                        processResponse(response, name); //문자열 응답으로온 문자열 처리하는 함수
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



    public void processResponse(String response , String name){
        Gson gson = new Gson();
        SixItemList sixitemlist = gson.fromJson(response , SixItemList.class); //받아온 json문자열을 gson을 이용해서 자바객체로 자동으로 변환
        if(posi == 4){
            for(int i =0 ;i<sixitemlist.StatisticSearch.row.size(); i++){
                SixItem sixItem = sixitemlist.StatisticSearch.row.get(i);
                //가독성 있게 time 가공하기
                //년도의 뒤에 2개 , 월만 나오게 가공
                String tempYM = sixItem.TIME;
                String year = tempYM.substring(2,4);
                String month = tempYM.substring(4,6);
                lables[i] = year +"."+month;
                data_val.add(new Entry(i,Float.parseFloat(sixItem.DATA_VALUE) - arr.get(i)));
            }
        } else {
            for (int i = 0; i < sixitemlist.StatisticSearch.row.size(); i++) {
                SixItem sixItem = sixitemlist.StatisticSearch.row.get(i);
                String tempYM = sixItem.TIME;
                String year = tempYM.substring(2,4);
                String month = tempYM.substring(4,6);
                lables[i] = year +"."+month;
                data_val.add(new Entry(i, Float.parseFloat(sixItem.DATA_VALUE)));
            }
        }

        LineDataSet lineDataSet = new LineDataSet(data_val, name);
        LineData lineData = new LineData(lineDataSet); ////엔트리를 넣어둔 리스트 데이터를 셋팅
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineData.setValueTextSize(10);

        lineChart.setData(lineData);   //차트에 데이터 셋팅

        //오른쪽 y축 비활성화
        YAxis yr= lineChart.getAxisRight();
        yr.setDrawLabels(false);
        yr.setDrawAxisLine(false);
        yr.setDrawGridLines(false);

        //X축 위치 설정
        XAxis x = lineChart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setValueFormatter(new IndexAxisValueFormatter(lables));
        x.setLabelCount(10);
        x.setTextSize(7);



        //x축 한칸당 나오게 하기  + 끝까지 나오게하기

        lineChart.invalidate();


    }

    public void processResponse2(String response) {
        Gson gson = new Gson();
        SixItemList sixitemlist = gson.fromJson(response, SixItemList.class); //받아온 json문자열을 gson을 이용해서 자바객체로 자동으로 변환
        for (int i = 0; i < sixitemlist.StatisticSearch.row.size(); i++) {
            SixItem sixItem = sixitemlist.StatisticSearch.row.get(i);
            arr.add(Float.parseFloat(sixItem.DATA_VALUE));
        }

    }
    /*
    public class GraphAxisValueFormatter extends IndexAxisValueFormatter {

         String[] mValues ;

        // 생성자 초기화
        GraphAxisValueFormatter(String[] values){
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value) {
            return mValues[(int) value];
        }


    }*/


}