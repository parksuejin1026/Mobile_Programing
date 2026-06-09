package com.cookandroid.class1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
// 양방향 액티비티 실습

public class MainActivity extends AppCompatActivity {

    // [수정] Second 액티비티를 식별하기 위한 고유 요청 코드 추가
    private static final int REQUEST_CODE_SECOND = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티");

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edt1 = (EditText) findViewById(R.id.edt1);
                EditText edt2 = (EditText) findViewById(R.id.edt2);

                Intent intent = new Intent(getApplicationContext(), Second.class);
                intent.putExtra("Num1", Integer.parseInt(edt1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(edt2.getText().toString()));

                // [수정] 기존 startActivity(intent, 0) 대신 결과를 받는 메서드로 교체
                startActivityForResult(intent, REQUEST_CODE_SECOND);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data); // 상위 메서드 호출 추가

        // [수정] 내가 보낸 요청이 맞는지(REQUEST_CODE_SECOND) 먼저 검증하는 조건 추가
        if (requestCode == REQUEST_CODE_SECOND && resultCode == RESULT_OK) {
            if (data != null) {
                int hap = data.getIntExtra("Hap", 0);
                Toast.makeText(getApplicationContext(), "합계 :" + hap, Toast.LENGTH_SHORT).show();
            }
        }
    }
}