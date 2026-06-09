package com.cookandroid.class3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
// Log 클래스 import
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Info 레벨: 화면 초기화 시작을 알림
        Log.i("액티비티 메시지", "onCreate() 호출 - UI 구성 및 리스너 등록 시작");

        Button btnDial = (Button) findViewById(R.id.btnDial);
        Button btnEnd = (Button) findViewById(R.id.btnEnd);

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Debug 레벨: 개발 중 특정 이벤트(클릭 등)가 정상 작동하는지 확인
                Log.d("액티비티 메시지", "btnDial 클릭됨: 01099433932로 전화를 시도합니다.");

                try {
                    Uri uri = Uri.parse("tel:01099433932");
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);
                } catch (Exception e) {
                    // Error 레벨: 만에 하나 인텐트 실행 중 크래시가 나면 원인을 출력하도록 예외처리
                    Log.e("액티비티 메시지", "전화 걸기 인텐트 실행 실패", e);
                }
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Debug 레벨: 종료 버튼 클릭 감지
                Log.d("액티비티 메시지", "btnEnd 클릭됨: finish()를 호출하여 액티비티를 종료합니다.");
                finish();
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        // Verbose 레벨: 아주 상세한 흐름을 추적할 때 사용
        Log.v("액티비티 메시지", "onStart() 호출 - 화면이 유저에게 보이기 직전");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Info 레벨: 앱이 유저와 상호작용할 수 있는 완전히 활성화된 상태
        Log.i("액티비티 메시지", "onResume() 호출 - 앱이 현재 포커스를 잡고 실행 중인 상태 (정상 완료)");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Warning 레벨: 전화를 걸러 다른 화면으로 넘어가거나, 유저가 앱을 가리는 경고/주의 상황
        Log.w("액티비티 메시지", "onPause() 호출 - 주의: 화면의 일부분이 가려지거나 다른 액티비티로 전환 중");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Info 레벨: 화면이 더 이상 유저에게 보이지 않음
        Log.i("액티비티 메시지", "onStop() 호출 - 앱이 백그라운드로 이동하여 보이지 않는 상태");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Info 레벨: 기존 대소문자 오류 수정 및 깔끔한 출력
        Log.i("액티비티 메시지", "onDestroy() 호출 - 메모리에서 액티비티가 완전히 소멸됨");
    }
}