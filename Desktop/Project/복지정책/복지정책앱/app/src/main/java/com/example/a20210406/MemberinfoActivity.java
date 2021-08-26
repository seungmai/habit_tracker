package com.example.a20210406;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

public class MemberinfoActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "MemberinfoActivity";
    private RadioGroup radioGroup_age, radioGroup_choice;
    public String age, choice, obstKiArray, obstAbtArray, trgterIndvdlArray, desireArray;
    public Spinner spinner_age, spinner_choice, spinner_obstKiArray, spinner_obstAbtArray, spinner_trgterIndvdlArray, spinner_desireArray;
    public String[] array1 = {"영유아", "아동", "청소년", "청년", "중장년", "노년"};
    public String[] array2 = {"해당없음", "여성", "임산부", "장애", "보훈대상자", "실업자"};
    public String[] array3 = {"해당없음", "지체", "시각", "청각", "언어", "지적", "뇌병변", "자폐성", "정신", "신장", "심장", "호흡기", "간", "안면", "장루", "간질"};
    public String[] array4 = {"해당없음", "심한 장애", "심하지 않은 장애"};
    public String[] array5 = {"해당없음", "한부모", "다문화", "조손", "새터민", "소년소녀가장", "독거노인"};
    public String[] array6 = {"해당없음", "안전", "건강", "일상생활", "가족관계", "사회적 관계", "경제", "교육", "고용", "생활환경", "법률 및 권익보장", "기타"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memberinfo);

        findViewById(R.id.button_gotomain).setOnClickListener(onClickListener);
        spinner_age = findViewById(R.id.age_spinner);
        spinner_choice = findViewById(R.id.choice_spinner);
        spinner_obstKiArray = findViewById(R.id.spinner_obstKiArray);
        spinner_obstAbtArray = findViewById(R.id.spinner_obstAbtArray);
        spinner_trgterIndvdlArray = findViewById(R.id.spinner_trgterIndvdlArray);
        spinner_desireArray = findViewById(R.id.spinner_desireArray);
        // Initialize Firebase Auth

        Spinner_age();
        Spinner_choice();
        spinner_obstKiArray();
        Spinner_obstAbtArray();
        Spinner_trgterIndvdlArray();
        Spinner_desireArray();


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        // FirebaseUser currentUser = mAuth.getCurrentUser();

    }
    //클릭시 아이디에 따른  화면이동
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_gotomain:
                    Log.e("클릭", "메인으로 클릭");
                    Log.e("age", "age" + age);
                    Log.e("choice", "choice" + choice);

                    profileUpdate();
//                    if(age.isEmpty())
//                        break;
                    MovetoMain();
                    break;
            }
        }
    };


    private void StartToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void MovetoMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //각 스피너에 있는 아이템에 따라 변수에 저장
    public void Spinner_age() {
        ArrayAdapter<String> adapter_age = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                array1
        );
        spinner_age.setAdapter(adapter_age);
        spinner_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), array1[i] + " 선택하셨습니다", Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    age = "001";
                } else if (i == 1) {
                    age = "002";
                } else if (i == 2) {
                    age = "003";
                } else if (i == 3) {
                    age = "004";
                } else if (i == 4) {
                    age = "005";
                } else if (i == 5) {
                    age = "006";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //각 스피너에 있는 아이템에 따라 변수에 저장
    public void spinner_obstKiArray() {
        ArrayAdapter<String> adapter_obstKiArray = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                array3
        );
        spinner_obstKiArray.setAdapter(adapter_obstKiArray);
        spinner_obstKiArray.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), array3[i] + " 선택하셨습니다", Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    obstKiArray = "";
                } else if (i == 1) {
                    obstKiArray = "10";
                } else if (i == 2) {
                    obstKiArray = "20";
                } else if (i == 3) {
                    obstKiArray = "30";
                } else if (i == 4) {
                    obstKiArray = "40";
                } else if (i == 5) {
                    obstKiArray = "50";
                } else if (i == 6) {
                    obstKiArray = "60";
                } else if (i == 7) {
                    obstKiArray = "70";
                } else if (i == 8) {
                    obstKiArray = "80";
                } else if (i == 9) {
                    obstKiArray = "90";
                } else if (i == 10) {
                    obstKiArray = "A0";
                } else if (i == 11) {
                    obstKiArray = "B0";
                } else if (i == 12) {
                    obstKiArray = "C0";
                } else if (i == 13) {
                    obstKiArray = "D0";
                } else if (i == 14) {
                    obstKiArray = "E0";
                } else if (i == 15) {
                    obstKiArray = "F0";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //각 스피너에 있는 아이템에 따라 변수에 저장
    public void Spinner_obstAbtArray() {
        ArrayAdapter<String> adapter_obstAbtArray = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                array4
        );
        spinner_obstAbtArray.setAdapter(adapter_obstAbtArray);
        spinner_obstAbtArray.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), array4[i] + " 선택하셨습니다", Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    obstAbtArray = "";
                } else if (i == 1) {
                    obstAbtArray = "10";
                }else if (i == 2) {
                    obstAbtArray = "20";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    //각 스피너에 있는 아이템에 따라 변수에 저장
    public void Spinner_trgterIndvdlArray() {
        ArrayAdapter<String> adapter_trgterIndvdlArray = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                array5
        );
        spinner_trgterIndvdlArray.setAdapter(adapter_trgterIndvdlArray);
        spinner_trgterIndvdlArray.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), array5[i] + " 선택하셨습니다", Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    trgterIndvdlArray = "";
                } else if (i == 1) {
                    trgterIndvdlArray = "001";
                } else if (i == 2) {
                    trgterIndvdlArray = "002";
                }
                else if (i == 3) {
                    trgterIndvdlArray = "003";
                }
                else if (i == 4) {
                    trgterIndvdlArray = "004";
                }
                else if (i == 5) {
                    trgterIndvdlArray = "005";
                }
                else if (i == 6) {
                    trgterIndvdlArray = "006";
                }
                else if (i == 7) {
                    trgterIndvdlArray = "007";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //각 스피너에 있는 아이템에 따라 변수에 저장
    public void Spinner_desireArray() {
        ArrayAdapter<String> adapter_desireArray = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                array6
        );
        spinner_desireArray.setAdapter(adapter_desireArray);
        spinner_desireArray.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), array6[i] + " 선택하셨습니다", Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    desireArray = "";
                } else if (i == 1) {
                    desireArray = "0000000";
                }else if (i == 2) {
                    desireArray = "1000000";
                }else if (i == 3) {
                    desireArray = "2000000";
                }else if (i == 4) {
                    desireArray = "3000000";
                }else if (i == 5) {
                    desireArray = "4000000";
                }else if (i == 6) {
                    desireArray = "5000000";
                }else if (i == 7) {
                    desireArray = "6000000";
                }else if (i == 8) {
                    desireArray = "7000000";
                }else if (i == 9) {
                    desireArray = "8000000";
                }else if (i == 10) {
                    desireArray = "9000000";
                }
                else if (i == 11) {
                    desireArray = "A000000";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //각 스피너에 있는 아이템에 따라 변수에 저장
    public void Spinner_choice() {
        ArrayAdapter<String> adapter_choice = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                array2
        );
        spinner_choice.setAdapter(adapter_choice);
        spinner_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), array2[i] + " 선택하셨습니다", Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    choice = "001";
                } else if (i == 1) {
                    choice = "002";
                } else if (i == 2) {
                    choice = "003";
                } else if (i == 3) {
                    choice = "004";
                } else if (i == 4) {
                    choice = "005";
                } else if (i == 5) {
                    choice = "006";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // 유저 아이디를 가져와서 그 유저에 대한 정보를 파이어베이스를 통해 저장
    private void profileUpdate() {
        String name = ((EditText) findViewById(R.id.editText_UserName)).getText().toString();

        if (name.length() > 0) {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            MemberIngoDB memberIngoDB = new MemberIngoDB(name, age, choice, obstKiArray, obstAbtArray, trgterIndvdlArray, desireArray);

            if (user != null) {
                db.collection("users").document(user.getUid()).set(memberIngoDB)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                StartToast("회원정보등록 성공");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                StartToast("회원정보등록 실패");
                                Log.w(TAG, "Error writing document", e);
                            }
                        });
            }


        } else {
            StartToast("회원정보를 선택해주세요");
            Log.e("age", age);
            Log.e("choice", choice);
            Log.e("name", name);
        }
    }
}

