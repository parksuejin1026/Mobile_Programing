package com.cookandroid.class1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        // 메인 액티비티에서 보낸 데이터 수신 및 더하기 연산
        Intent intent = getIntent();
        final int hapValue = intent.getIntExtra("Num1", 0) + intent.getIntExtra("Num2", 0);

        Button btn2 = (Button) findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // [수정] 목적지(MainActivity.class)를 적지 않고 빈 Intent를 생성합니다.
                Intent outIntent = new Intent();

                // 돌려줄 결과 데이터 삽입
                outIntent.putExtra("Hap", hapValue);

                // 결과 설정 후 액티비티 종료
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
