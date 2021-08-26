package com.example.a20210406;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class MoreInfo extends AppCompatActivity {

    public String id;
    public String key = "JUmCqi82GL8KMJTfC%2B474upkTunXBlZSInKPPLCcXRPk5LBt6tSjc%2B1BmHz5kOFlo2Yq2blkuAuVhW77u0d8rQ%3D%3D";
    String data3, data2, data_service_name, data_service_explain, data_service_support, data_service_howToApply;
    String dial1, dial2, Apply1,Apply1_2 ,Apply2, Apply2_2,  Apply3, Apply3_2, Apply4, Apply4_2, Apply5;
    String sumdial2, sumdial3;
    TextView text, text_service_target, moreinfo_textview_num, moreinfo_textview_service_name, moreinof_textview_service_explanation, moreinof_textview_service_support, moreinof_textview_service_howToApply, moreinfo_textview2_num;
    TextView moreinof_textview_service_howToApply1_1, moreinof_textview_service_howToApply1_2, moreinof_textview_service_howToApply2_1, moreinof_textview_service_howToApply2_2, moreinof_textview_service_howToApply3_1, moreinof_textview_service_howToApply3_2, moreinof_textview_service_howToApply4_1, moreinof_textview_service_howToApply4_2;
    ImageView call1, call2;
    XmlPullParser xpp;
    LinearLayout apply1_layout2, apply1_layout3, apply1_layout4;
    public boolean Boolean_layout2, Boolean_layout3, Boolean_layout4;
    public int count;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                MovetoMain();
                return true;
            case R.id.menu_info:
                MovetoMemberInfo();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moreinfo_service);
        text = (TextView) findViewById(R.id.moreinfo_textview);
        text_service_target = (TextView) findViewById(R.id.moreinof_service_target_);

        moreinfo_textview_service_name = (TextView) findViewById(R.id.moreinof_service_name);
        moreinof_textview_service_explanation = (TextView) findViewById(R.id.moreinof_service_explanation);
        moreinof_textview_service_support = (TextView) findViewById(R.id.moreinof_service_support);
        //moreinof_textview_service_howToApply = (TextView) findViewById(R.id.moreinof_service_howToApply);
        moreinof_textview_service_howToApply1_1 = (TextView) findViewById(R.id.moreinof_service_howToApply1_1);
        moreinof_textview_service_howToApply1_2 = (TextView) findViewById(R.id.moreinof_service_howToApply1_2);
        moreinof_textview_service_howToApply2_1 = (TextView) findViewById(R.id.moreinof_service_howToApply2_1);
        moreinof_textview_service_howToApply2_2 = (TextView) findViewById(R.id.moreinof_service_howToApply2_2);
        moreinof_textview_service_howToApply3_1 = (TextView) findViewById(R.id.moreinof_service_howToApply3_1);
        moreinof_textview_service_howToApply3_2 = (TextView) findViewById(R.id.moreinof_service_howToApply3_2);
        moreinof_textview_service_howToApply4_1 = (TextView) findViewById(R.id.moreinof_service_howToApply4_1);
        moreinof_textview_service_howToApply4_2 = (TextView) findViewById(R.id.moreinof_service_howToApply4_2);
        moreinfo_textview2_num = (TextView) findViewById(R.id.moreinfo_textview2);
        call1 = (ImageView)findViewById(R.id.imageView6);
        call2 = (ImageView)findViewById(R.id.imageView5);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar3);
        apply1_layout2 = (LinearLayout)findViewById(R.id.apply2_layout);
        apply1_layout3 = (LinearLayout)findViewById(R.id.apply3_layout);
        apply1_layout4 = (LinearLayout)findViewById(R.id.apply4_layout);

        setSupportActionBar(tb);

        Intent intent = getIntent();
        id = intent.getStringExtra("moreinfo");

        Toast.makeText(getApplicationContext(), "넘겨온 id : " + id, Toast.LENGTH_SHORT).show();




        //전화 아이콘을 클릭할시 전화를 거는 기능
        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String[] array_dial2 = dial1.split("-");
//                for(int i=0; i<array_dial2.length; i++){
//                    sumdial3 = sumdial3 + array_dial2[i];
//                }
                Intent intent_dial1 = new Intent();
                intent_dial1.setAction(Intent.ACTION_DIAL);
                intent_dial1.setData(Uri.parse("tel:"+dial1));
                Log.e("",""+dial1);
                startActivity(intent_dial1);
                sumdial3 = null;
            }
        });
        //전화 아이콘을 클릭할시 전화를 거는 기능
        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String[] array_dial2 = dial2.split("-");
//                for(int i=0; i<array_dial2.length; i++){
//                     sumdial2 = sumdial2 + array_dial2[i];
//                }
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" +dial2));
                startActivity(intent);
                sumdial2 = null;

            }
        });

        new Thread(new Runnable() {


            @Override

            public void run() {

                // TODO Auto-generated method stub

                data3 = getXmlData_num(); //아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기
                data2 = getXmlData2();
                data_service_name = getXmlData_name();
                data_service_explain = getXmlData_expalain();
                data_service_support = getXmlData_support();
                data_service_howToApply = getXmlData_howToApply();

                //전화번호를 받은 문자열을 "/"로 나눈다.
                String[] array_num = data3.split("/");

                Split_Apply();




                Log.e("전화번호", "분류" + data3);


                //UI Thread(Main Thread)를 제외한 어떤 Thread도 화면을 변경할 수 없기때문에

                //runOnUiThread()를 이용하여 UI Thread가 TextView 글씨 변경하도록 함

                runOnUiThread(new Runnable() {


                    @Override

                    public void run() {

                        // TODO Auto-generated method stub




                        //나눈 전화번호가 있는 배열의 길이만큼 전화번호를 출력하고 가시화, 불가시화를 정한다.
                        for(int i = 0; i<array_num.length; i++){
                            if(i ==0){
                                dial1 = array_num[i];
                                text.setText(array_num[0]);
                                moreinfo_textview2_num.setEnabled(false);
                                call1.setVisibility(View.VISIBLE);


                            }
                            if(i ==1){
                                moreinfo_textview2_num.setEnabled(true);
                                dial2 = array_num[i];

                                moreinfo_textview2_num.setText(array_num[1]);
                                call2.setVisibility(View.VISIBLE);





                            }


                        }





                        //TextView에 문자열  data 출력
                        text_service_target.setText(data2);  //TextView에 문자열  data 출력
                        moreinfo_textview_service_name.setText(data_service_name);
                        moreinof_textview_service_explanation.setText(data_service_explain);
                        moreinof_textview_service_support.setText(data_service_support);
                        //moreinof_textview_service_howToApply.setText(data_service_howToApply);
                        moreinof_textview_service_howToApply1_1.setText(Apply1);
                        moreinof_textview_service_howToApply1_2.setText(Apply1_2);
                        moreinof_textview_service_howToApply2_1.setText(Apply2);
                        moreinof_textview_service_howToApply2_2.setText(Apply2_2);
                        moreinof_textview_service_howToApply3_1.setText(Apply3);
                        moreinof_textview_service_howToApply3_2.setText(Apply3_2);

                        moreinof_textview_service_howToApply4_1.setText(Apply4);
                        moreinof_textview_service_howToApply4_2.setText(Apply4_2);

                        //불린 변수를 통해서 신청방법의 레이아웃을 가시화 한다.
                        if(Boolean_layout4 ==true){
                            apply1_layout4.setVisibility(View.VISIBLE);
                        }
                        if(Boolean_layout3 ==true){
                            apply1_layout3.setVisibility(View.VISIBLE);
                        }
                        if(Boolean_layout2 ==true){
                            apply1_layout2.setVisibility(View.VISIBLE);
                        }


                    }

                });


            }

        }).start();


    }

    private void MovetoMemberInfo() {
        Intent intent = new Intent(this, MemberinfoActivity.class);
        startActivity(intent);
    }

    private void MovetoMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //전화번호를 받는 함수
    String getXmlData_num() {
        StringBuffer buffer = new StringBuffer();

        String query = "%EC%A0%84%EB%A0%A5%EB%A1%9C";

        String queryUrl = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + key
                + "&callTp=D"
                + "&servId=" + id;
        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//테그 이름 얻어오기

                        Log.e("데이터 파싱", "데이터 파싱중 : moreinfo  data3");
                        if (tag.equals("inqplCtadrList")) {
                            Log.e("", "tag " + tag);

                            count = 1;
                        }
                        if (count == 1) {
                            if (tag.equals("servSeDetailLink")) {

                                xpp.next();
                                buffer.append(xpp.getText());
                                buffer.append("/");
                                Log.e("data3", "연락처" + xpp.getText());
                                count = 2;
                            }
                        } else
                            Log.e("", "---------------------------------");

                        // 첫번째 검색결과

                        break;


                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //테그 이름 얻어오기

                        if (tag.equals("inqplCtadrList")) ;// 첫번째 검색결과종료..줄바꿈
                        break;

                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return buffer.toString();
        //StringBuffer 문자열 객체 반환


    }

    //서비스 이름을 받는 함수
    String getXmlData_name() {
        StringBuffer buffer = new StringBuffer();

        String query = "%EC%A0%84%EB%A0%A5%EB%A1%9C";

        String queryUrl = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + key
                + "&callTp=D"
                + "&servId=" + id;
        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:

                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//테그 이름 얻어오기

                        Log.e("데이터 파싱", "데이터 파싱중 : moreinfo  data3");
                        if (tag.equals("wantedDtl")) {
                            Log.e("", "tag " + tag);


                        }

                        if (tag.equals("servNm")) {

                            xpp.next();
                            buffer.append(xpp.getText());


                        } else
                            Log.e("", "---------------------------------");

                        // 첫번째 검색결과

                        break;


                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //테그 이름 얻어오기

                        if (tag.equals("wantedDtl")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;

                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return buffer.toString();
        //StringBuffer 문자열 객체 반환


    }

    //서비스 설명을 받는 함수
    String getXmlData_expalain() {
        StringBuffer buffer = new StringBuffer();

        String query = "%EC%A0%84%EB%A0%A5%EB%A1%9C";

        String queryUrl = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + key
                + "&callTp=D"
                + "&servId=" + id;
        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:

                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//테그 이름 얻어오기

                        Log.e("데이터 파싱", "데이터 파싱중 : moreinfo  data3");
                        if (tag.equals("wantedDtl")) {
                            Log.e("", "tag " + tag);


                        }

                        if (tag.equals("servDgst")) {

                            xpp.next();
                            buffer.append(xpp.getText());


                        } else
                            Log.e("", "---------------------------------");

                        // 첫번째 검색결과

                        break;


                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //테그 이름 얻어오기

                        if (tag.equals("wantedDtl")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;

                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return buffer.toString();
        //StringBuffer 문자열 객체 반환


    }

    //지원내용을 받는 함수
    String getXmlData_support() {
        StringBuffer buffer = new StringBuffer();

        String query = "%EC%A0%84%EB%A0%A5%EB%A1%9C";

        String queryUrl = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + key
                + "&callTp=D"
                + "&servId=" + id;
        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:

                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//테그 이름 얻어오기

                        Log.e("데이터 파싱", "데이터 파싱중 : moreinfo  data3");
                        if (tag.equals("wantedDtl")) {
                            Log.e("", "tag " + tag);


                        }

                        if (tag.equals("alwServCn")) {

                            xpp.next();
                            buffer.append(xpp.getText());


                        } else
                            Log.e("", "---------------------------------");

                        // 첫번째 검색결과

                        break;


                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //테그 이름 얻어오기

                        if (tag.equals("wantedDtl")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;

                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return buffer.toString();
        //StringBuffer 문자열 객체 반환


    }

    //신청방법을 받는 함수
    String getXmlData_howToApply() {
        StringBuffer buffer = new StringBuffer();

        String query = "%EC%A0%84%EB%A0%A5%EB%A1%9C";

        String queryUrl = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + key
                + "&callTp=D"
                + "&servId=" + id;
        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//테그 이름 얻어오기

                        Log.e("데이터 파싱", "데이터 파싱중 : moreinfo  data3");
                        if (tag.equals("applmetList")) {
                            Log.e("", "tag " + tag);

                            count = 2;
                        }
                        if (count == 2) {
                            if (tag.equals("servSeDetailNm")) {

                                xpp.next();
                                buffer.append(xpp.getText());
                                buffer.append("\n");
                                Log.e("data3", "연락처" + xpp.getText());
                                count = 3;
                            }
                        } else
                            Log.e("", "---------------------------------");

                        // 첫번째 검색결과

                        break;


                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //테그 이름 얻어오기

                        if (tag.equals("inqplCtadrList")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;

                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return buffer.toString();
        //StringBuffer 문자열 객체 반환


    }

    //지원가능 대상자를 받는 함수
    String getXmlData2() {
        StringBuffer buffer = new StringBuffer();
        String query = "%EC%A0%84%EB%A0%A5%EB%A1%9C";

        String queryUrl = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + key
                + "&callTp=D"
                + "&servId=" + id;
        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//테그 이름 얻어오기

                        Log.e("데이터 파싱", "데이터 파싱중 : moreinfo  data2");
                        if (tag.equals("wantedDtl")) ;// 첫번째 검색결과
                        else if (tag.equals("tgtrDtlCn")) {


                            xpp.next();
                            buffer.append(xpp.getText()+"에게 지원합니다");//servId 요소의 TEXT 읽어와서 문자열버퍼에 추가

                            buffer.append("\n"); //줄바꿈 문자 추가


                        }
//
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //테그 이름 얻어오기

                        if (tag.equals("wantedDtl")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return buffer.toString();//StringBuffer 문자열 객체 반환


    }


    //신청방법 문자열을 나누고 그에 따라 레이아웃에 출력하는 함수
    public void Split_Apply(){
        int idx1 = data_service_howToApply.indexOf("1");
        int idx2 = data_service_howToApply.indexOf("2");
        int idx3 = data_service_howToApply.indexOf("3");
        int idx4 = data_service_howToApply.indexOf("4");
        int idx5 = data_service_howToApply.indexOf("5");


        //1이 있을때
        if(data_service_howToApply.indexOf("1")!=-1) {
            //뒤에 2가 있다면 인덱스 위치를 구해서 나누고 그 값을 변수에 저장
            if(data_service_howToApply.indexOf("2")!=-1) {
                Apply1 = data_service_howToApply.substring(1, idx2-1);
                Log.e("apply1", "apply1 " + Apply1);

                //변수에 "(" 가 있다면 문자영을 나눠서 저장
                if(Apply1.indexOf("(")!=-1){
                    int idx = Apply1.indexOf("(");
                    Apply1_2 = Apply1.substring(idx+1);
                    Apply1 = Apply1.substring(1, idx);

                    Log.e("apply1", "apply1 " + Apply1 +"   " +Apply1_2);
                }
                //변수에 ":" 가 있다면 문자영을 나눠서 저장
                if(Apply1.indexOf(":")!=-1){
                    int idx = Apply1.indexOf(":");
                    Apply1_2 = Apply1.substring(idx+1);
                    Apply1 = Apply1.substring(1, idx);

                    Log.e("apply1", "apply1 " + Apply1 +"   " +Apply1_2);
                }

            }
            //뒤에 2가 없다면 저당
            else {
                Apply1 = data_service_howToApply.substring(1);
                if(Apply1.indexOf("(")!=-1){
                    int idx = Apply1.indexOf("(");
                    Apply1_2 = Apply1.substring(idx+1);
                    Apply1 = Apply1.substring(1, idx);

                    Log.e("apply1", "apply1 " + Apply1 +"   " +Apply1_2);
                }
                if(Apply1.indexOf(":")!=-1){
                    int idx = Apply1.indexOf(":");
                    Apply1_2 = Apply1.substring(idx+1);
                    Apply1 = Apply1.substring(1, idx);

                    Log.e("apply1", "apply1 " + Apply1 +"   " +Apply1_2);
                }
            }
        }else {

        }
        if(data_service_howToApply.indexOf("2")!=-1) {
            if(data_service_howToApply.indexOf("3")!=-1) {
                Apply2 = data_service_howToApply.substring(idx2+1, idx3-1);
                if(Apply2.indexOf("(")!=-1){
                    int idx = Apply2.indexOf("(");
                    Apply2_2 = Apply2.substring(idx+1);
                    Apply2 = Apply2.substring(1, idx);
                    Boolean_layout2 = true;


                    Log.e("apply2", "apply2 " + Apply2 +"   " +Apply2_2);
                }
                if(Apply2.indexOf(":")!=-1){
                    int idx = Apply2.indexOf(":");
                    Apply2_2 = Apply2.substring(idx+1);
                    Apply2 = Apply2.substring(1, idx);
                    Boolean_layout2 = true;
                    Log.e("apply2", "apply2 " + Apply2 +"   " +Apply2_2);
                }
            }
            else {
                Apply2 = data_service_howToApply.substring(idx2+1);
                if(Apply2.indexOf("(")!=-1){
                    int idx = Apply2.indexOf("(");
                    Apply2_2 = Apply2.substring(idx+1);
                    Apply2 = Apply2.substring(1, idx);
                    Boolean_layout2 = true;
                    Log.e("apply2", "apply2 " + Apply2 +"   " +Apply2_2);
                }
                if(Apply2.indexOf(":")!=-1){
                    int idx = Apply2.indexOf(":");
                    Apply2_2 = Apply2.substring(idx+1);
                    Apply2 = Apply2.substring(1, idx);
                    Boolean_layout2 = true;
                    Log.e("apply2", "apply2 " + Apply2 +"   " +Apply2_2);
                }
            }
        }else {

        }
        if(data_service_howToApply.indexOf("3")!=-1) {

            if(data_service_howToApply.indexOf("4")!=-1) {
                Apply3 = data_service_howToApply.substring(idx3+1, idx4-1);
                if(Apply3.indexOf("(")!=-1){
                    int idx = Apply3.indexOf("(");
                    Apply3_2 = Apply3.substring(idx+1);
                    Apply3 = Apply3.substring(1, idx);
                    Boolean_layout3 = true;
                    Log.e("apply3", "apply3 " + Apply3 +"   " +Apply3_2);
                }
                if(Apply3.indexOf(":")!=-1){
                    int idx = Apply3.indexOf(":");
                    Apply3_2 = Apply3.substring(idx+1);
                    Apply3 = Apply3.substring(1, idx);
                    Boolean_layout3 = true;
                    Log.e("apply3", "apply3 " + Apply3 +"   " +Apply3_2);
                }
            }
            else {
                Apply3 = data_service_howToApply.substring(idx3+1);
                if(Apply3.indexOf("(")!=-1){
                    int idx = Apply3.indexOf("(");
                    Apply3_2 = Apply3.substring(idx+1);
                    Apply3 = Apply3.substring(1, idx);
                    Boolean_layout3 = true;
                    Log.e("apply3", "apply3 " + Apply3 +"   " +Apply3_2);
                }
                if(Apply3.indexOf(":")!=-1){
                    int idx = Apply3.indexOf(":");
                    Apply3_2 = Apply3.substring(idx+1);
                    Apply3 = Apply3.substring(1, idx);
                    Boolean_layout3 = true;
                    Log.e("apply3", "apply3 " + Apply3 +"   " +Apply3_2);
                }
            }
        }else {

        }
        if(data_service_howToApply.indexOf("4")!=-1) {
            if(data_service_howToApply.indexOf("5")!=-1) {
                Apply4 = data_service_howToApply.substring(idx4+1, idx5-1);
                if(Apply4.indexOf("(")!=-1){
                    int idx = Apply4.indexOf("(");
                    Apply4_2 = Apply4.substring(idx+1);
                    Apply4 = Apply4.substring(1, idx);
                    Boolean_layout4 = true;
                    Log.e("apply4", "apply4 " + Apply4 +"   " +Apply4_2);
                }
                if(Apply4.indexOf(":")!=-1){
                    int idx = Apply4.indexOf(":");
                    Apply4_2 = Apply4.substring(idx+1);
                    Apply4 = Apply4.substring(1, idx);
                    Boolean_layout4 = true;
                    Log.e("apply4", "apply4 " + Apply4 +"   " +Apply4_2);
                }
            }
            else {
                Apply4 = data_service_howToApply.substring(idx4+1);
                if(Apply4.indexOf("(")!=-1){
                    int idx = Apply4.indexOf("(");
                    Apply4_2 = Apply4.substring(idx+1);
                    Apply4 = Apply4.substring(1, idx);
                    Boolean_layout4 = true;
                    Log.e("apply4", "apply4 " + Apply4 +"   " +Apply4_2);
                }else {}

                if(Apply4.indexOf(":")!=-1){
                    int idx = Apply4.indexOf(":");
                    Apply4_2 = Apply4.substring(idx+1);
                    Apply4 = Apply4.substring(1, idx);
                    Boolean_layout4 = true;
                    Log.e("apply4", "apply4 " + Apply4 +"   " +Apply4_2);
                } else {}
            }
        }else {

        }
        if(data_service_howToApply.indexOf("4")!=-1) {
            Apply5 = data_service_howToApply.substring(idx5+1);
            Log.e("신청방법","신청방법 5");
        }
        else {
            Apply5 = data_service_howToApply.substring(idx5+1);
        }
       // Log.e("신청방법", "신청방법"+Apply1+"\n"+Apply2+"\n"+Apply3+"\n"+Apply4+"\n"+Apply5+"\n");
        Log.e("신청방법", "신청방법"+Apply1_2+" "+Apply2_2+" "+Apply3_2+" "+Apply4_2+" "+Apply5+"\n");


    }


}

