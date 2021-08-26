package com.example.a20210406;
//깃허브 실험용


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20210406.Recyclerview_item.Item;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.Document;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public String name = "", age = "", choice = "", obstKiArray = "", obstAbtArray = "", trgterIndvdlArray = "", desireArray = "", searchWrd = "";
    public TextView textView_test;
    public Array info;

    //공공 api를 사용하기 위한 인증키
    String key = "JUmCqi82GL8KMJTfC%2B474upkTunXBlZSInKPPLCcXRPk5LBt6tSjc%2B1BmHz5kOFlo2Yq2blkuAuVhW77u0d8rQ%3D%3D";
    String data;
    EditText edit;
    //TextView text;
    XmlPullParser xpp;
    public boolean find_check = false;
    public String moreinfo_data;

    public String dataKey = "JUmCqi82GL8KMJTfC%2B474upkTunXBlZSInKPPLCcXRPk5LBt6tSjc%2B1BmHz5kOFlo2Yq2blkuAuVhW77u0d8rQ%3D%3D";
    public String requestUrl;
    public String findUrl;
    ArrayList<Item> list = null;
    Item bus = null;
    RecyclerView recyclerView;
    public String srchKeyCode = "001";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu) ;

        return true ;
    }
    // 눌리는 아이템에 따라서 페이지 이동 (툴바)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home :
                MovetoMain();
                return true ;
            case R.id.menu_info :
                MovetoMemberInfo();
                return true ;

            default :
                return super.onOptionsItemSelected(item) ;
        }
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        textView_test = (TextView) findViewById(R.id.textView4);
        edit = (EditText) findViewById(R.id.edit);
        //text= (TextView)findViewById(R.id.result);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar3) ;
        setSupportActionBar(tb) ;



///////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////
        //로그인시 유저의 아이디를 가져오고 그 아이디에 대한 정보를 가져온다.
        //만약 유저의 정보가 없을시 정보 입력 페이지로 이동시킨다.
        if (user == null) {
            myStartActivity(SignUpActivcity.class);
        } else {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            //
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.exists()) {
                                Log.e(TAG, "DocumentSnapshot data: " + document.getData());


                                name = document.getString("name");
                                age = document.getString("age");
                                choice = document.getString("choice");
                                obstKiArray = document.getString("obstKiArray");
                                obstAbtArray = document.getString("obstAbtArray");
                                trgterIndvdlArray = document.getString("trgterIndvdlArray");
                                desireArray = document.getString("desireArray");


                                Log.e(TAG, "데이터 확인 " + name);
                                MyAsyncTask myAsyncTask = new MyAsyncTask();
                                myAsyncTask.execute();
                                textView_test.setText(name
                                );
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        data = getXmlData();

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                            }
                                        });
                                    }
                                }).start();

                            } else {
                                Log.d(TAG, "No such document");
                                myStartActivity(MemberinfoActivity.class);
                            }
                        }

                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }
// findViewById(R.id.button).setOnClickListener(onClickListener);

//        AsyncTask
//        MyAsyncTask myAsyncTask = new MyAsyncTask();
//        myAsyncTask.execute();


        //MyAdapter adapter = new MyAdapter(getApplicationContext(), list);

    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            //공공 api에 검색하는 url 사용자가 입력한 정보에 따라서 자동으로 검색
            requestUrl = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + key
                    + "&srchKeyCode" + srchKeyCode
                    + "&searchWrd=" + searchWrd
                    + "&callTp=L&pageNum=1&numOfRows=10&lifeArray=" + age
                    + "&charTrgterArray=" + choice
                    + "&obstKiArray=" + obstKiArray
                    + "&obstAbtArray=" + obstAbtArray
                    + "&trgterIndvdlArray=" + trgterIndvdlArray
                    + "&desireArray=" + desireArray;


            //사용자가 검색할시 검색어에 맞는 복지서비스 검색
            findUrl = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + key
                    + "&srchKeyCode" + srchKeyCode
                    + "&searchWrd=" + searchWrd
                    + "&callTp=L";

            if (find_check == true)
                requestUrl = findUrl;
            try {
                boolean b_servNm = false;
                boolean b_servId = false;
                boolean b_servDgst = false;
                boolean b_servDtlLink = false;


                // xml 파싱 데이터를 알맞은 태그로 받아와서 아이템 배열에 저장
                URL url = new URL(requestUrl);
                Log.e("xml파싱", "xm파싱 url" + requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<Item>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("servList") && bus != null) {
                                list.add(bus);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("servList")) {
                                bus = new Item();
                            }
                            if (parser.getName().equals("servNm")) b_servNm = true;
                            if (parser.getName().equals("servId")) b_servId = true;
                            if (parser.getName().equals("servDgst")) b_servDgst = true;
                            if (parser.getName().equals("servDtlLink")) b_servDtlLink = true;
                            break;
                        case XmlPullParser.TEXT:
                            if (b_servNm) {
                                Log.e("리사이클러뷰", "인식");
                                bus.setservNm(parser.getText());
                                bus.setlogo2(R.drawable.logo2);
                                b_servNm = false;
                            } else if (b_servId) {
                                bus.setservId(parser.getText());
                                b_servId = false;
                            } else if (b_servDgst) {
                                bus.setservDgst(parser.getText());
                                b_servDgst = false;
                            } else if (b_servDtlLink) {
                                bus.setservDtlLink(parser.getText());
                                b_servDtlLink = false;
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        public void onPostExecute(String s) {
            super.onPostExecute(s);

            //어답터 연결
            //리사이클러뷰를 클릭시 그 복지서비스에 대한 자세한 내용이 보이는 페이지로 이동
            MyAdapter adapter = new MyAdapter(getApplicationContext(), list);
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position, String moreinfo) {

                    Log.e("test ", "test 선택 인덱스 num" + position);
                    Log.e("test ", "test 선택 인덱스 value" + moreinfo);
                    moreinfo_data = moreinfo;
                    Intent intent = new Intent(MainActivity.this, MoreInfo.class);
                    intent.putExtra("moreinfo", moreinfo_data);
                    setResult(RESULT_OK, intent);
                    startActivity(intent);

                }


            });


        }


    }


    //    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()){
//                case R.id.button:
//                    Log.e("클릭", "클릭");
//                    MovetoInfo();
//                    break;
//
//
//            }
//        }
//    };

    //클릭시 그 클릭한 id에 따라 동작
    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                searchWrd = edit.getText().toString();
                find_check = true;
                //searchWrd = URLEncoder.encode(searchWrd, "UTF-8");
                Log.e("검색버튼", "검색어 : " + searchWrd);


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //  data = getXmlData();

                        MyAsyncTask myAsyncTask = new MyAsyncTask();
                        myAsyncTask.execute();
                        Log.e("Url", "find : " + requestUrl);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }
                }).start();

                break;
        }
    }

    String getXmlData() {
        StringBuffer buffer = new StringBuffer();
        String str = edit.getText().toString();//EditText에 작성된 Text얻어오기
        String location = URLEncoder.encode(str);
        String query = "%EC%A0%84%EB%A0%A5%EB%A1%9C";

        String queryUrl = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=" + key
                + "&srchKeyCode" + srchKeyCode
                + "&searchWrd=" + searchWrd
                + "&callTp=L&pageNum=1&numOfRows=10&lifeArray=" + age
                + "&charTrgterArray=" + choice
                + "&obstKiArray=" + obstKiArray
                + "&obstAbtArray=" + obstAbtArray
                + "&trgterIndvdlArray=" + trgterIndvdlArray
                + "&desireArray=" + desireArray;
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

                        Log.e("데이터 파싱", "데이터 파싱중");
                        if (tag.equals("servList")) ;// 첫번째 검색결과
                        else if (tag.equals("servId")) {
                            buffer.append("서비스ID : ");
                            xpp.next();
                            buffer.append(xpp.getText());//servId 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가

                        } else if (tag.equals("servNm")) {
                            buffer.append("서비스명 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");

                        } else if (tag.equals("jurMnofNm")) {
                            buffer.append("소관부처명 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("jurOrgNm")) {
                            buffer.append("소관조직명 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("inqNum")) {
                            buffer.append("조회수 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("servDgst")) {
                            buffer.append("서비스 요약 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("servDtlLink")) {
                            buffer.append("서비스상세링크 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("svcfrstRegTs")) {
                            buffer.append("서비스등록일 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
//
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //테그 이름 얻어오기

                        if (tag.equals("servList")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        buffer.append("파싱 끝\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환


    }

    private void MovetoMoreInfo() {
        Intent intent = new Intent(this, MoreInfo.class);
        startActivity(intent);
    }
    private void MovetoMemberInfo() {
        Intent intent = new Intent(this, MemberinfoActivity.class);
        startActivity(intent);
    }
    private void MovetoMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //---------------------------------------------------------------------------------------------------

    //리사이클러뷰 어댑터
    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        public ArrayList<Item> mList;
        private LayoutInflater mInflate;
        private Context context;
        public String moreinfo;



        private OnItemClickListener mListener = null;


        // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
        public void setOnItemClickListener(OnItemClickListener listener) {
            this.mListener = listener;
        }

        public interface OnItemClickListener {
            void onItemClick(View v, int position, String moreinfo);
        }


        public MyAdapter(Context context, ArrayList<Item> itmes) {
            this.mList = itmes;
            this.mInflate = LayoutInflater.from(context);
            this.context = context;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflate.inflate(R.layout.recyclerview_item, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);

            return viewHolder;
        }

        //아이템 내용을 설정
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            //binding
            //holder.servId.setText(mList.get(position).servId);
            Drawable drawable = ContextCompat.getDrawable(context, mList.get(position).geticonlogo2());
            holder.icon.setBackground(drawable);
            holder.servNm.setText(mList.get(position).servNm);



;

            //Click event
        }


        @Override
        public int getItemCount() {
            return mList.size();
        }

        //ViewHolder
        public class MyViewHolder extends RecyclerView.ViewHolder {
            //public TextView servId;
            public TextView servNm;
            public TextView servDgst;
            public TextView servDtlLink;
            public ImageView icon;



            public MyViewHolder(View itemView) {
                super(itemView);



                servNm = itemView.findViewById(R.id.tv_servNm);
               icon = itemView.findViewById(R.id.cardview_image);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            // 리스너 객체의 메서드 호출.
                            if (mListener != null) {
                                Log.e("클릭이벤트", "포지션별 ID정보 : " + mList.get(pos).servId);

                                mListener.onItemClick(v, pos, mList.get(pos).servId);


                            }

                        }
                    }
                });


            }
        }

    }


}